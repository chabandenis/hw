package ru.otus.hw.ex06.repositories;

import ru.otus.hw.ex06.models.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book save(Book book);

    Book create(String title, long authorId, Set<Long> genres);

    Book update(Long bookId, String title, long authorId, Set<Long> genres);

    void deleteById(long id);

}
