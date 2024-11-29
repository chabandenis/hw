package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.AuthorConverter;
import ru.otus.hw.ex06.converters.BookConverter;
import ru.otus.hw.ex06.converters.GenreConverter;
import ru.otus.hw.ex06.dto.BookDto;
import ru.otus.hw.ex06.exceptions.EntityNotFoundException;
import ru.otus.hw.ex06.models.Book;
import ru.otus.hw.ex06.repositories.AuthorRepository;
import ru.otus.hw.ex06.repositories.BookRepository;
import ru.otus.hw.ex06.repositories.GenreRepository;
import ru.otus.hw.ex06.repositories.JdbcBookRepository;

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

    private final JdbcBookRepository jdbcBookRepository;

    private final BookConverter bookConverter;

    private final GenreConverter genreConverter;

    private final AuthorConverter authorConverter;

    @Override
    @Transactional
    public Optional<BookDto> findById(long id) {
        return Optional.ofNullable(bookConverter.toDto(bookRepository.findById(id).get()));
    }

    @Override
    @Transactional
    public List<BookDto> findAll() {
        return jdbcBookRepository.findAll().stream().map(bookConverter::toDto).toList();
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

        //List<Comment> comments = new ArrayList<>();

        var book = new Book();
        book.setGenres(genres);
        book.setId(id);
        //book.setCommentBooks();
        book.setAuthor(author);
        book.setTitle(title);

        return bookConverter.toDto(bookRepository.save(book));
    }
}
