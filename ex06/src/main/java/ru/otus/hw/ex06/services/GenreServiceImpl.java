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

    @Override
    @Transactional
    public List<GenreDto> findAll() {

        return genreRepository.findAll()
                .stream()
                .map(GenreConverter::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<GenreDto> findAllByIds(Set<Long> ids) {

        return genreRepository.findAllByIds(ids)
                .stream()
                .map(GenreConverter::toDto)
                .toList();
    }
}
