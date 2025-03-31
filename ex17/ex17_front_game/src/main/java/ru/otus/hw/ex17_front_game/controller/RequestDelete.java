package ru.otus.hw.ex17_front_game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.net.URI;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.HEADER_X_REQUEST_ID;

public interface RequestDelete {
    // удалить игру
    @DeleteMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // http://localhost:8080/api/game/7
    Mono<Void> delete(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @PathVariable Long id);
}