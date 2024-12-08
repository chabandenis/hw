package ru.otus.hw.ex06.services;

import ru.otus.hw.ex06.dto.CommentDto;
import ru.otus.hw.ex06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentDto> findById(long id);

    List<CommentDto> findCommentsByBookId(long bookId);

    CommentDto save(Comment commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
