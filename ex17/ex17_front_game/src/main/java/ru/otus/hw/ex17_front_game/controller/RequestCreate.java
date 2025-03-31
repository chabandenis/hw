package ru.otus.hw.ex17_front_game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_front_game.dto.GameDto;
import ru.otus.hw.ex17_front_game.dto.GamesCreateDto;

import java.net.URI;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.HEADER_X_REQUEST_ID;

public interface RequestCreate {

    // создать игру
    // http://localhost:8080/api/game
    // {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "", consumes = "application/json")
    Mono<ResponseEntity<GameDto>> create(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @RequestBody GamesCreateDto gamesCreateDto);

}