package ru.otus.hw.ex17_front_game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_front_game.dto.GameDto;

import java.net.URI;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.HEADER_X_REQUEST_ID;

public interface RequestGetOne {
    // информация об игре по заданному идентификатору
    // http://localhost:8080/api/game/1
    @GetMapping(value = "/{id}", consumes = "application/json")
    Mono<ResponseEntity<GameDto>> getOne(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @PathVariable Long id);
}