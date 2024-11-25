package ru.otus.hw.ex05.repositories;

import ru.otus.hw.ex05.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenreRepository {
    List<Genre> findAll();

    List<Genre> findAllByIds(Set<Long> ids);
}
