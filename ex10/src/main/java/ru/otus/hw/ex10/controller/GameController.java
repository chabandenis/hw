package ru.otus.hw.ex10.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex10.dto.GameDto;
import ru.otus.hw.ex10.dto.InputXYDTO;
import ru.otus.hw.ex10.services.GameService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // выбрать игры в которые играли два игрока
    @GetMapping("/api/games/{mainUser}/{secondUser}")
    public List<GameDto> getAll(@PathVariable Long mainUser,
                                @PathVariable Long secondUser
    ) {
        return gameService.getAllByUsers(mainUser, secondUser);
    }

    // выполнить ход
    @RequestMapping(value = "/api/games/step", method = RequestMethod.POST)
    public GameDto doStep(@RequestBody InputXYDTO inputXYDTO) {
        return gameService.doStep(inputXYDTO);
    }

    // создать игру
    @RequestMapping(value = "/api/games/new/{mainUser}/{secondUser}", method = RequestMethod.POST)
    public GameDto newGame(@PathVariable Long mainUser,
                           @PathVariable Long secondUser) {
        return gameService.newGame(mainUser, secondUser);
    }

    // удалить игру
    @PostMapping("/api/games/delete/{id}")
    public GameDto delete(@PathVariable Long id) {
        return gameService.delete(id);
    }

    // информация об игре по заданному идентификатору
    @GetMapping("/api/games/{id}")
    public GameDto getOne(@PathVariable Long id) {
        return gameService.getOne(id);
    }
}