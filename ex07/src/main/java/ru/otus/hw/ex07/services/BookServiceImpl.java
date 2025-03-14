package ru.otus.hw.ex07.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex07.converters.BookConverter;
import ru.otus.hw.ex07.converters.CommentConverter;
import ru.otus.hw.ex07.dto.BookDto;
import ru.otus.hw.ex07.exceptions.EntityNotFoundException;
import ru.otus.hw.ex07.models.Book;
import ru.otus.hw.ex07.models.Genre;
import ru.otus.hw.ex07.repositories.AuthorRepository;
import ru.otus.hw.ex07.repositories.BookRepository;
import ru.otus.hw.ex07.repositories.CommentRepository;
import ru.otus.hw.ex07.repositories.GenreRepository;

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

    private final BookConverter bookConverter;

    private final CommentRepository commentRepository;

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @Override
    @Transactional(readOnly = true)
    public Optional<BookDto> findById(long id) {
        var bookDto = bookRepository.findById(id);

        if (bookDto.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(bookConverter.toDto(bookDto.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        List<BookDto> books =
                bookRepository.findAll()
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

    private List<Genre> findGenres(Set<Long> genresIds) {
        List<Genre> genres = new ArrayList<>();
        for (Long genreId : genresIds) {
            var genre = genreRepository.findById(genreId);
            if (genre.isEmpty()) {
                throw new EntityNotFoundException("Отсутствует жанр с id=" + genreId);
            }
            genres.add(genre.get());
        }

        return genres;
    }

    @Override
    @Transactional
    public BookDto update(long id, String title, long authorId, Set<Long> genresIds) {
        var book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new EntityNotFoundException("Отсутствует книга с идентификатором id=" + id);
        }
        var author = authorRepository.findById(authorId);
        if (author.isEmpty()) {
            throw new EntityNotFoundException("Отсутствует книга с идентификатором id=" + id);
        }

        List<Genre> genres = findGenres(genresIds);

        if (!book.get().getTitle().contentEquals(title)) {
            book.get().setTitle(title);
        }
        if (book.get().getAuthor().getId() != authorId) {
            book.get().setAuthor(author.get());
        }
        book.get().setGenres(genres);
        return bookConverter.toDto(bookRepository.save(book.get()));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteByBookId(id);
        bookRepository.deleteById(id);
    }

    private BookDto save(long id, String title, long authorId, Set<Long> genresIds) {
        if (isEmpty(genresIds)) {
            throw new IllegalArgumentException("Genres ids must not be null");
        }

        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genres = genreRepository.findByIdIn(genresIds);
        if (isEmpty(genres) || genresIds.size() != genres.size()) {
            throw new EntityNotFoundException("One or all genres with ids %s not found".formatted(genresIds));
        }

        var book = new Book();
        book.setGenres(genres);
        book.setId(id);
        book.setAuthor(author);
        book.setTitle(title);

        return bookConverter.toDto(bookRepository.save(book));
    }
}
