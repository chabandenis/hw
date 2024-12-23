package ru.otus.hw.ex07.services;

import ru.otus.hw.ex07.dto.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentDto> findById(long id);

    List<CommentDto> findCommentsByBookId(long bookId);

    //CommentDto save(Comment commentDto);

    CommentDto create(long bookId, String comment);

    CommentDto update(long commentId, String comment);


    void deleteById(long id);

    void deleteByBookId(long bookId);
}
