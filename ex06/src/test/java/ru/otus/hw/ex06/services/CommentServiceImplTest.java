package ru.otus.hw.ex06.services;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.dto.CommentDto;
import ru.otus.hw.ex06.models.Author;
import ru.otus.hw.ex06.models.Book;
import ru.otus.hw.ex06.models.Comment;
import ru.otus.hw.ex06.repositories.JpaCommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({CommentServiceImpl.class, JpaCommentRepository.class, CommentConverter.class})
@Transactional(propagation = Propagation.NEVER)
class CommentServiceImplTest {
    private static final long BOOK_ID_FOR_DELETE = 3;
    private static final long COMMENT_ID = 1;
    private static final long COMMENT_FOR_SAVE = 1;
    private static final long COMMENT_FOR_DELETE = 1;
    private static final long COMMENT_ID_NOT_EXISTS = 33;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentConverter commentConverter;

    @Autowired
    private EntityManager em;

    @Test
    void findById() {
        CommentDto expectedComment = new CommentDto(COMMENT_ID, "comment 1", 1);

        var foundComment = commentService.findById(COMMENT_ID);

        assertThat(foundComment).isPresent();

        assertThat(foundComment.get()).
                usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("поиск комментариев по несуществующему идентификатору книги")
    void findCommentsByBookIdWhichNotExists() {
        assertThat(commentService.findCommentsByBookId(COMMENT_ID_NOT_EXISTS)).isEmpty();
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

    /*
    @Test
    void save() {
        Comment comment = new Comment(0, "comment 3",
                new Book(COMMENT_FOR_SAVE, "", new Author(1, ""), List.of()));
        commentService.save(comment);

        var commentFound = commentService.findById(comment.getId());

        assertThat(commentFound).isPresent();

        assertThat(commentFound.get()).usingRecursiveComparison().isEqualTo(commentConverter.toDto(comment));
    }

     */

    @Test
    void deleteById() {
        Optional<CommentDto> comment = commentService.findById(COMMENT_FOR_DELETE);
        assertThat(comment.isPresent()).isEqualTo(true);

        commentService.deleteById(COMMENT_FOR_DELETE);
        Optional<CommentDto> commentAfterDelete = commentService.findById(COMMENT_ID);

        assertThat(commentAfterDelete.isPresent()).isEqualTo(false);

    }

    @Test
    void deleteByBookId() {
        List<CommentDto> expectedComment = commentService.findCommentsByBookId(BOOK_ID_FOR_DELETE);

        commentService.deleteByBookId(BOOK_ID_FOR_DELETE);

        for (CommentDto comment : expectedComment) {
            var commentFind = commentService.findById(comment.getId());
            assertThat(commentFind.isPresent()).isEqualTo(false);
        }
    }

    @Test
    void create() {
        Comment comment = new Comment(0, "comment 3", new Book(1, "", new Author(), List.of()));

        CommentDto commentDto = commentService.create(comment.getBook().getId(), comment.getText());

        var commentFound = commentService.findById(commentDto.getId());

        assertThat(commentFound).isPresent();

        assertThat(commentFound.get().getText()).isEqualTo(comment.getText());
        assertThat(commentFound.get().getBookId()).isEqualTo(comment.getBook().getId());

        // вернуть в исходное состояние
        commentService.deleteById(commentDto.getId());
    }

    @Test
    void update() {
        var commentDto = commentService.findById(1);

        assertThat(commentDto).isPresent();

        String oldComment = commentDto.get().getText();

        commentDto.get().setText("qwerty");

        var commentFound = commentService.update(commentDto.get().getId(),
                commentDto.get().getBookId(),
                commentDto.get().getText());

        assertThat(commentFound).usingRecursiveComparison().isEqualTo(commentDto.get());

        // вернуть в исходное состояние
        commentDto.get().setText(oldComment);
        commentService.update(commentDto.get().getId(),
                commentDto.get().getBookId(),
                commentDto.get().getText());
    }

}