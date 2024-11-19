package ru.otus.hw.ex05.services;

import ru.otus.hw.ex05.models.Author;
import ru.otus.hw.ex05.models.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
