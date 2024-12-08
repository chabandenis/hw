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
import ru.otus.hw.ex06.repositories.JpaAuthorRepository;
import ru.otus.hw.ex06.repositories.JpaBookRepository;
import ru.otus.hw.ex06.repositories.JpaCommentBookRepository;
import ru.otus.hw.ex06.repositories.JpaGenreRepository;

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

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void findById() {
        assertThat(bookService.findById(BOOK_ID_NOT_EXISTS).isPresent()).isEqualTo(false);
    }
}