package ru.otus.hw.ex17_front_game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;
import ru.otus.hw.ex17_front_game.dto.GameDto;

import java.net.URI;
import java.util.List;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.HEADER_X_REQUEST_ID;

public interface RequestGamesForUsers {

    // выбрать совместные игры
    // http://localhost:8080/api/game/1/2
    @GetMapping(value = "/{mainUser}/{secondUser}"/*, consumes = "application/json"*/)
    Flux<GameDto> getGamesForUsers(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId,
            @RequestHeader("Authorization") String authorizationHeader,
            URI baseUri,
            @PathVariable Long mainUser, @PathVariable Long secondUser);

}