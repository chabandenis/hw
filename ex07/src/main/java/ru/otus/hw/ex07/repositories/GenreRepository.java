package ru.otus.hw.ex07.repositories;

import ru.otus.hw.ex07.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenreRepository {
    List<Genre> findAll();

    List<Genre> findAllByIds(Set<Long> ids);
}
