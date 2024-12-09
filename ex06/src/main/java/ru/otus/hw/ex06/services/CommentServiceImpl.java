package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.dto.CommentDto;
import ru.otus.hw.ex06.models.Comment;
import ru.otus.hw.ex06.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    private final CommentConverter commentConverter;

    @Override
    @Transactional(readOnly = true)
    public Optional<CommentDto> findById(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(commentConverter.toDto(comment.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentsByBookId(long bookId) {
        return commentRepository.findCommentByBookId(bookId)
                .stream()
                .map(commentConverter::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CommentDto create(long bookId, String comment) {
        return commentConverter.toDto(commentRepository.create(bookId, comment));
    }

    @Override
    @Transactional
    public CommentDto update(long commentId, long bookId, String comment) {
        return commentConverter.toDto(commentRepository.update(commentId, bookId, comment));
    }

/*
    @Override
    @Transactional
    public CommentDto save(Comment comment) {
        return commentConverter.toDto(commentRepository.save(comment));
    }
*/

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByBookId(long bookId) {
        commentRepository.deleteByBookId(bookId);
    }
}
