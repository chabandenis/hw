package ru.otus.hw.ex17_front_game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_front_game.dto.CoordinatesDto;
import ru.otus.hw.ex17_front_game.dto.GameDto;

import java.net.URI;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.HEADER_X_REQUEST_ID;

public interface RequestStep {
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
}