package ru.otus.hw.ex07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex07.models.CommentBook;

import java.util.List;
import java.util.Optional;

public interface CommentBookRepository extends JpaRepository<CommentBook, Long> {
    Optional<CommentBook> findById(long id);

    List<CommentBook> findCommentByBookId(long bookId);

    CommentBook save(CommentBook commentDto);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
