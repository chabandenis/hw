package ru.otus.hw.ex09.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.services.GameService;

@Slf4j
@Controller
//@RequestMapping("/")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // игра прописана в БД
    //http://localhost:8080/game/1
    @GetMapping("/game/{id}")
    public /*@ResponseBody */String getOne(@PathVariable Long id, Model model) {
        var game = gameService.getOne(id);
        log.info(game.toString());
        model.addAttribute("game", game);
        return "list";
    }
}

