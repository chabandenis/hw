package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.GenreConverter;
import ru.otus.hw.ex06.dto.GenreDto;
import ru.otus.hw.ex06.repositories.GenreRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    private final GenreConverter genreConverter;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {

        return genreRepository.findAll()
                .stream()
                .map(genreConverter::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAllByIds(Set<Long> ids) {

        return genreRepository.findAllByIds(ids)
                .stream()
                .map(genreConverter::toDto)
                .toList();
    }
}
