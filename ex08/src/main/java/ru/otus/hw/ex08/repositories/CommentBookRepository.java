package ru.otus.hw.ex07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex07.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentBookRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(long id);

    List<Comment> findCommentByBookId(long bookId);

    Comment save(Comment commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
