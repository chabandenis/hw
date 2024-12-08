package ru.otus.hw.ex06.services;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.dto.CommentDto;
import ru.otus.hw.ex06.models.Comment;
import ru.otus.hw.ex06.repositories.JpaCommentBookRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({CommentServiceImpl.class, JpaCommentBookRepository.class, CommentConverter.class})
@Transactional(propagation = Propagation.NEVER)
class CommentServiceImplTest {
    private static final long COMMENT_ID = 1;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentService commentService;

//    @Autowired
//    private CommentBookRepository commentBookRepository;

    @Test
    void findById() {
    }

    @Test
    @DisplayName("поиск комментариев по несуществующему идентификатору книги")
    void findCommentsByBookIdWhichNotExists() {
        assertThat(commentService.findCommentsByBookId(33)).isEmpty();
    }

    @Test
    @DisplayName("поиск комментариев по существующему идентификатору книги")
    void findCommentsByBookId() {
        List<CommentDto> expectedComment =
                List.of(
                        new CommentDto(1, "comment 1", 1),
                        new CommentDto(2, "comment 2", 1));
        assertThat(commentService.findCommentsByBookId(1)).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
        Optional<CommentDto> comment = commentService.findById(COMMENT_ID);
        assertThat(comment.isPresent()).isEqualTo(true);

        commentService.deleteById(COMMENT_ID);
        Optional<CommentDto> commentAfterDelete = commentService.findById(COMMENT_ID);

        assertThat(commentAfterDelete.isPresent()).isEqualTo(false);

    }

    @Test
    void deleteByBookId() {
    }
}