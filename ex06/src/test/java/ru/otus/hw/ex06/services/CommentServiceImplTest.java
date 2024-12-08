package ru.otus.hw.ex06.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.repositories.CommentBookRepository;
import ru.otus.hw.ex06.repositories.JpaAuthorRepository;
import ru.otus.hw.ex06.repositories.JpaBookRepository;
import ru.otus.hw.ex06.repositories.JpaCommentBookRepository;
import ru.otus.hw.ex06.repositories.JpaGenreRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import({CommentServiceImpl.class, JpaCommentBookRepository.class, CommentConverter.class})
class CommentServiceImplTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private JpaCommentBookRepository jpaCommentBookRepository;

    @Test
    void findById() {
    }

    @Test
    @DisplayName("поиск комментариев по несуществующему идентификатору криги")
    void findCommentsByBookIdWhichNotExists() {
        assertThat(jpaCommentBookRepository.findCommentByBookId(33)).isEmpty();
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteByBookId() {
    }
}