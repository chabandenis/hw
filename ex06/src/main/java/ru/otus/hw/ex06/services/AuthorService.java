package ru.otus.hw.ex06.services;

import ru.otus.hw.ex06.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
