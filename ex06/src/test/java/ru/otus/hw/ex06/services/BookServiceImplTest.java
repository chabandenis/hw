package ru.otus.hw.ex06.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.AuthorConverter;
import ru.otus.hw.ex06.converters.BookConverter;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.converters.GenreConverter;
import ru.otus.hw.ex06.dto.AuthorDto;
import ru.otus.hw.ex06.dto.BookDto;
import ru.otus.hw.ex06.dto.GenreDto;
import ru.otus.hw.ex06.repositories.JpaAuthorRepository;
import ru.otus.hw.ex06.repositories.JpaBookRepository;
import ru.otus.hw.ex06.repositories.JpaCommentBookRepository;
import ru.otus.hw.ex06.repositories.JpaGenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BookServiceImpl.class,
        JpaBookRepository.class,
        BookConverter.class,
        JpaAuthorRepository.class,
        JpaGenreRepository.class,
        AuthorConverter.class,
        GenreConverter.class,
        CommentConverter.class,
        CommentServiceImpl.class,
        JpaCommentBookRepository.class
})
@Transactional(propagation = Propagation.NEVER)
class BookServiceImplTest {

    private final static long BOOK_ID_NOT_EXISTS = 33;
    private final static long BOOK_ID_EXISTS = 1;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void findById() {
        assertThat(bookService.findById(BOOK_ID_NOT_EXISTS).isPresent()).isEqualTo(false);
    }

    @Test
    void findAll() {
        BookDto expectedBook =
                new BookDto(1,
                        "BookTitle_1",
                        new AuthorDto(1, "Author_1"),
                        List.of(new GenreDto(1, "Genre_1"),
                                new GenreDto(2, "Genre_2")
                        ));

        var foundBook = bookService.findById(BOOK_ID_EXISTS);

        assertThat(foundBook).isPresent();

        assertThat(foundBook.get()).usingRecursiveComparison().isEqualTo(expectedBook);

    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}