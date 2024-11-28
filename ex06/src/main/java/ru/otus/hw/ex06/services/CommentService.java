package ru.otus.hw.ex06.services;

import ru.otus.hw.ex06.models.CommentBook;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentBook> findById(long id);

    List<CommentBook> findCommentsByBookId(long bookId);

    CommentBook save(CommentBook commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
