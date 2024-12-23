package ru.otus.hw.ex07.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex07.converters.CommentConverter;
import ru.otus.hw.ex07.dto.CommentDto;
import ru.otus.hw.ex07.models.Comment;
import ru.otus.hw.ex07.repositories.BookRepository;
import ru.otus.hw.ex07.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentConverter commentConverter;

    private final BookRepository bookRepository;


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
        var book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            throw new EntityNotFoundException("отсутствует книга с id " + bookId);
        }

        var savedComment = commentRepository.save(new Comment(0, comment, book.get()));

        return commentConverter.toDto(savedComment);
    }

    @Override
    @Transactional
    public CommentDto update(long commentId, String comment) {
        var commentInDb = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Отсутствует комментарий с id=" + commentId));

        commentInDb.setText(comment);

        return commentConverter.toDto(commentRepository.save(commentInDb));
    }

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
