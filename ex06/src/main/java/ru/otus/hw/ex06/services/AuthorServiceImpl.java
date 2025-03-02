package ru.otus.hw.ex06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.AuthorConverter;
import ru.otus.hw.ex06.dto.AuthorDto;
import ru.otus.hw.ex06.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final AuthorConverter authorConverter;

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorConverter::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthorDto> findById(long id) {
        return Optional.ofNullable(authorConverter.toDto(authorRepository.findById(id).get()));
    }
}
