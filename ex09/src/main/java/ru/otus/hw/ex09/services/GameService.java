package ru.otus.hw.ex09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.mapper.GameMapper;
import ru.otus.hw.ex09.models.Game;
import ru.otus.hw.ex09.repositories.GameRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameMapper gameMapper;

    private final GameRepository gameRepository;

    public GameDto getOne(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        return gameMapper.toGameDto(gameOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }
}
