package ru.otus.hw.ex17_front_game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_front_game.dto.CoordinatesDto;
import ru.otus.hw.ex17_front_game.dto.GameDto;
import ru.otus.hw.ex17_front_game.dto.GamesCreateDto;

import java.net.URI;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.HEADER_X_REQUEST_ID;

public interface GameInfo {

    // выбрать совместные игры
    // http://localhost:8080/api/game/1/2
    @GetMapping(value = "/{mainUser}/{secondUser}", consumes = "application/json")
    Flux<GameDto> getGamesForUsers(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @PathVariable Long mainUser, @PathVariable Long secondUser);

    // выполнить ход
    // http://localhost:8080/api/game/1
    // {"x1":"D","y1":1,"x2":"F","y2":1}
    @PutMapping(value = "/{gameId}", consumes = "application/json")
    Mono<ResponseEntity<GameDto>> step(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @PathVariable Long gameId,
            @RequestBody CoordinatesDto coordinatesDto);

    // создать игру
    // http://localhost:8080/api/game
    // {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "", consumes = "application/json")
    Mono<ResponseEntity<GameDto>> create(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @RequestBody GamesCreateDto gamesCreateDto);

    // удалить игру
    @DeleteMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // http://localhost:8080/api/game/7
    Mono<Void> delete(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @PathVariable Long id);

    // информация об игре по заданному идентификатору
    // http://localhost:8080/api/game/1
    @GetMapping(value = "/{id}", consumes = "application/json")
    Mono<ResponseEntity<GameDto>> getOne(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @PathVariable Long id);
}