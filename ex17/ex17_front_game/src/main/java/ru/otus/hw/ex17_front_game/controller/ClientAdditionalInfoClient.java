package ru.otus.hw.ex17_front_game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw.ex17_front_game.model.ClientData;


import java.net.URI;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.HEADER_X_REQUEST_ID;

public interface ClientAdditionalInfoClient {

    @GetMapping(value = "/additional-info", consumes = "application/json")
    ClientData additionalInfo(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId, URI baseUri, @RequestParam("name") String nameVal);
}
