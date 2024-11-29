package ru.otus.hw.ex06.services;

import ru.otus.hw.ex06.dto.GenreDto;

import java.util.List;
import java.util.Set;

public interface GenreService {
    List<GenreDto> findAll();

    List<GenreDto> findAllByIds(Set<Long> ids);
}
