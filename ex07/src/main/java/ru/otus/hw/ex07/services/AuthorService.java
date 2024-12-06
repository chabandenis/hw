package ru.otus.hw.ex07.services;

import ru.otus.hw.ex07.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDto> findAll();

    Optional<AuthorDto> findById(long id);
}
