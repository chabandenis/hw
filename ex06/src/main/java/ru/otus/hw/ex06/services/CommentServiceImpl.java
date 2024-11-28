package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.dto.CommentBookDto;
import ru.otus.hw.ex06.models.CommentBook;
import ru.otus.hw.ex06.repositories.CommentBookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentBookRepository commentBookRepository;

    private final CommentConverter commentConverter;

    @Override
    @Transactional
    public Optional<CommentBookDto> findById(long id) {
        return Optional.ofNullable(commentConverter.toDto(commentBookRepository.findById(id).get()));
    }

    @Override
    @Transactional
    public List<CommentBookDto> findCommentsByBookId(long bookId) {
        return commentBookRepository.findCommentByBookId(bookId)
                .stream()
                .map(commentConverter::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CommentBookDto save(CommentBook commentBook) {
        return commentConverter.toDto(commentBookRepository.save(commentBook));
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
