package ru.otus.hw.ex07.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex07.converters.CommentConverter;
import ru.otus.hw.ex07.dto.CommentDto;
import ru.otus.hw.ex07.models.Comment;
import ru.otus.hw.ex07.repositories.CommentBookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentBookRepository commentBookRepository;

    private final CommentConverter commentConverter;

    @Override
    @Transactional(readOnly = true)
    public Optional<CommentDto> findById(long id) {
        return Optional.ofNullable(commentConverter.toDto(commentBookRepository.findById(id).get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentsByBookId(long bookId) {
        return commentBookRepository.findCommentByBookId(bookId)
                .stream()
                .map(commentConverter::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CommentDto save(Comment comment) {
        return commentConverter.toDto(commentBookRepository.save(comment));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentBookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByBookId(long bookId) {
        commentBookRepository.deleteByBookId(bookId);
    }
}
