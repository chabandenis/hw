package ru.otus.hw.ex06.repositories;

import ru.otus.hw.ex06.models.CommentBook;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<CommentBook> findById(long id);

    List<CommentBook> findCommentByBookId(long bookId);

    CommentBook save(CommentBook commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
