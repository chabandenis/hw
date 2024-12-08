package ru.otus.hw.ex06.services;

import ru.otus.hw.ex06.dto.CommentBookDto;
import ru.otus.hw.ex06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentBookDto> findById(long id);

    List<CommentBookDto> findCommentsByBookId(long bookId);

    CommentBookDto save(Comment commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
