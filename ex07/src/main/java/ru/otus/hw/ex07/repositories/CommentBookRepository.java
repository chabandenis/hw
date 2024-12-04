package ru.otus.hw.ex07.repositories;

import ru.otus.hw.ex07.models.CommentBook;

import java.util.List;
import java.util.Optional;

public interface CommentBookRepository {
    Optional<CommentBook> findById(long id);

    List<CommentBook> findCommentByBookId(long bookId);

    CommentBook save(CommentBook commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
