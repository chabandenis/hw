package ru.otus.hw.ex07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex07.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByBookId(long bookId);

    void deleteByBookId(long bookId);
}
