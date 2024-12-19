package ru.otus.hw.ex06.repositories;

import ru.otus.hw.ex06.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
