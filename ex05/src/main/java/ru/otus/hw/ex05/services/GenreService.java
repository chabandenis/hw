package ru.otus.hw.ex05.services;

import ru.otus.hw.ex05.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenreService {
    List<Genre> findAll();

    List<Genre> findAllByIds(Set<Long> ids);
}
