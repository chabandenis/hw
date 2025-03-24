package ru.otus.hw.ex17_front_game.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_front_game.dto.CoordinatesDto;
import ru.otus.hw.ex17_front_game.dto.GameDto;
import ru.otus.hw.ex17_front_game.dto.GamesCreateDto;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    // выбрать совместные игры
    // http://localhost:8080/api/game/1/2
    @GetMapping("/{mainUser}/{secondUser}")
    public Flux<GameDto> getGamesForUsers(@PathVariable Long mainUser, @PathVariable Long secondUser) {
        return null;
        //return gameRepositoryCustom.findAll(mainUser, secondUser);
    }

    // выполнить ход
    // http://localhost:8080/api/game/1
    // {"x1":"D","y1":1,"x2":"F","y2":1}
    @PutMapping(value = "/{gameId}")
    public Mono<ResponseEntity<GameDto>> step(@PathVariable Long gameId,
                                              @RequestBody CoordinatesDto coordinatesDto) {
        return null;
        //return gameServiceStep.step(gameId, coordinatesDto);
    }

    // создать игру
    // http://localhost:8080/api/game
    // {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "")
    public Mono<ResponseEntity<GameDto>> create(@RequestBody GamesCreateDto gamesCreateDto) {
        return null;
        //return gameServiceCreate.create(gamesCreateDto);
    }

    // удалить игру
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // http://localhost:8080/api/game/7
    public Mono<Void> delete(@PathVariable Long id) {
        return null;
        //return gameService.delete(id);
    }

    // информация об игре по заданному идентификатору
    // http://localhost:8080/api/game/1
    @GetMapping("/{id}")
    public Mono<ResponseEntity<GameDto>> getOne(@PathVariable Long id) {
        return null;
//        return gameServiceGetOne.getOne(id);
    }
}
