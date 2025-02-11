package ru.otus.hw.ex12sd.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex12sd.dto.GameDto;
import ru.otus.hw.ex12sd.dto.game.CoordinatesDto;
import ru.otus.hw.ex12sd.dto.game.GamesCreateDto;
import ru.otus.hw.ex12sd.services.GameService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    // выбрать совместные игры
    // http://localhost:8080/api/game/1/2
    @GetMapping("/{mainUser}/{secondUser}")
    public List<GameDto> getGamesForUsers(
            @PathVariable Long mainUser,
            @PathVariable Long secondUser) {
        return gameService.getGamesForUsers(
                mainUser,
                secondUser);
    }

    // выполнить ход
    // http://localhost:8080/api/game/1
    // {"x1":"D","y1":1,"x2":"F","y2":1}
    @PutMapping(value = "/{gameId}")
    public GameDto step(@PathVariable Long gameId,
                        @RequestBody @Valid CoordinatesDto coordinatesDto) {
        return gameService.step(gameId, coordinatesDto);
    }

    // создать игру
    // http://localhost:8080/api/game
    // {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "")
    public ResponseEntity<GameDto> create(@RequestBody @Valid GamesCreateDto gamesCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(gamesCreateDto));
    }

    // удалить игру
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // http://localhost:8080/api/game/7
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }

    // информация об игре по заданному идентификатору
    // http://localhost:8080/api/game/1
    @GetMapping("/{id}")
    public GameDto getOne(@PathVariable Long id) {
        return gameService.getOne(id);
    }
}