package ru.otus.hw.ex06.repositories;

import ru.otus.hw.ex06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

    List<Comment> findCommentByBookId(long bookId);

    Comment save(Comment commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
