package ru.otus.hw.ex07.services;

import ru.otus.hw.ex07.dto.CommentBookDto;
import ru.otus.hw.ex07.models.CommentBook;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentBookDto> findById(long id);

    List<CommentBookDto> findCommentsByBookId(long bookId);

    CommentBookDto save(CommentBook commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
