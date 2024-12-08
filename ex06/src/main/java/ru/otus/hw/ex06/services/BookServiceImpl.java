package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.BookConverter;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.dto.BookDto;
import ru.otus.hw.ex06.exceptions.EntityNotFoundException;
import ru.otus.hw.ex06.models.Book;
import ru.otus.hw.ex06.repositories.AuthorRepository;
import ru.otus.hw.ex06.repositories.BookRepository;
import ru.otus.hw.ex06.repositories.GenreRepository;
import ru.otus.hw.ex06.repositories.JpaBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final JpaBookRepository jpaBookRepository;

    private final BookConverter bookConverter;

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @Override
    @Transactional(readOnly = true)
    public Optional<BookDto> findById(long id) {
        BookDto bookDto = bookConverter.toDto(bookRepository.findById(id).get());
        return Optional.ofNullable(bookDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        List<BookDto> books =
                jpaBookRepository.findAll()
                        .stream()
                        .map(bookConverter::toDto)
                        .toList();
        return books;
    }

    @Override
    @Transactional
    public BookDto insert(String title, long authorId, Set<Long> genresIds) {
        return save(0, title, authorId, genresIds);
    }

    @Override
    @Transactional
    public BookDto update(long id, String title, long authorId, Set<Long> genresIds) {
        return save(id, title, authorId, genresIds);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    private BookDto save(long id, String title, long authorId, Set<Long> genresIds) {
        if (isEmpty(genresIds)) {
            throw new IllegalArgumentException("Genres ids must not be null");
        }

        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genres = genreRepository.findAllByIds(genresIds);
        if (isEmpty(genres) || genresIds.size() != genres.size()) {
            throw new EntityNotFoundException("One or all genres with ids %s not found".formatted(genresIds));
        }

        var book = new Book();
        book.setGenres(genres);
        book.setId(id);
        book.setAuthor(author);
        book.setTitle(title);
//        book.setComment(new ArrayList<>());

        return bookConverter.toDto(bookRepository.save(book));
    }
}
