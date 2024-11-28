package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex06.models.Comment;
import ru.otus.hw.ex06.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
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
