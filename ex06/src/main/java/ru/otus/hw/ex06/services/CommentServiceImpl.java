package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.models.CommentBook;
import ru.otus.hw.ex06.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentConverter commentConverter;

    @Override
    public Optional<CommentBook> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<CommentBook> findCommentsByBookId(long bookId) {
        return commentRepository.findCommentByBookId(bookId)
                .stream()
                .map(commentConverter::toDto)
                .toList();
    }

    @Override
    public CommentBook save(CommentBook commentDto) {
        return commentRepository.save(commentDto);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteByBookId(long bookId) {
        commentRepository.deleteByBookId(bookId);
    }
}
