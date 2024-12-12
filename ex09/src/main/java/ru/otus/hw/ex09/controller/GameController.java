package ru.otus.hw.ex09.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.services.GameService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/game/{id}")
    public @ResponseBody GameDto getOne(@PathVariable Long id) {
        return gameService.getOne(id);
    }
}

