package ru.otus.hw.ex17_game.controller;

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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_game.dto.GameDto;
import ru.otus.hw.ex17_game.dto.game.CoordinatesDto;
import ru.otus.hw.ex17_game.dto.game.GamesCreateDto;
import ru.otus.hw.ex17_game.repositories.game.GameRepositoryCustom;
import ru.otus.hw.ex17_game.services.Game.GameService;
import ru.otus.hw.ex17_game.services.Game.GameServiceCreate;
import ru.otus.hw.ex17_game.services.Game.GameServiceGetOne;
import ru.otus.hw.ex17_game.services.Game.GameServiceStep;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/game")
@RequestMapping("")
public class GameController {

    private final GameServiceCreate gameServiceCreate;

    private final GameService gameService;

    private final GameServiceGetOne gameServiceGetOne;

    private final GameServiceStep gameServiceStep;

    private final GameRepositoryCustom gameRepositoryCustom;

    // выбрать совместные игры
    // http://localhost:8080/api/game/1/2
    @GetMapping("/{mainUser}/{secondUser}")
    public Flux<GameDto> getGamesForUsers(@PathVariable Long mainUser, @PathVariable Long secondUser) {
        return gameRepositoryCustom.findAll(mainUser, secondUser);
    }

    // выполнить ход
    // http://localhost:8080/api/game/1
    // {"x1":"D","y1":1,"x2":"F","y2":1}
    @PutMapping(value = "/{gameId}")
    public Mono<ResponseEntity<GameDto>> step(@PathVariable Long gameId,
                                              @RequestBody @Valid CoordinatesDto coordinatesDto) {
        return gameServiceStep.step(gameId, coordinatesDto);
    }

    // создать игру
    // http://localhost:8080/api/game
    // {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "")
    public Mono<ResponseEntity<GameDto>> create(@RequestBody @Valid GamesCreateDto gamesCreateDto) {
        return gameServiceCreate.create(gamesCreateDto);
    }

    // удалить игру
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // http://localhost:8080/api/game/7
    public Mono<Void> delete(@PathVariable Long id) {
        return gameService.delete(id);
    }

    // информация об игре по заданному идентификатору
    // http://localhost:8080/api/game/1
    @GetMapping("/{id}")
    public Mono<ResponseEntity<GameDto>> getOne(@PathVariable Long id) {

        return gameServiceGetOne.getOne(id);
    }
}
