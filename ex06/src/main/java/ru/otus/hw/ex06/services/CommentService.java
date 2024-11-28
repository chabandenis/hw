package ru.otus.hw.ex06.services;

import ru.otus.hw.ex06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(long id);

    List<Comment> findByBookId(long bookId);

    Comment save(Comment comment);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
