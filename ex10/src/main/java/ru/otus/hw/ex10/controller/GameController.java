package ru.otus.hw.ex10.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex10.dto.GameDto;
import ru.otus.hw.ex10.dto.InputXYDTO;
import ru.otus.hw.ex10.dto.fromWeb.GameActionDto;
import ru.otus.hw.ex10.logic.Cache;
import ru.otus.hw.ex10.mapper.GameMapper;
import ru.otus.hw.ex10.services.GameService;
import ru.otus.hw.ex10.services.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    private final InputXYDTO inputXYDTO;

    private final Cache cache;

    private final UserService userService;


    private Long gameId;

    // создать игру
    @PostMapping(value = "/api/games/actions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GameDto gameNew( @RequestBody GameActionDto gameActionDto) {
        var game = GameMapper.toGameDto(gameService.newGame());
        return game;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String gameDelete(@RequestParam("id") Long id, Model model) {
        if (id == null) {
            throw new NotFoundException("В запросе на удаление отсутствует id игры");
        }

        if (cache.getLogin() == null) {
            throw new NotFoundException("Отсутствует сохраненный логин пользователя");
        }

        try {
            gameService.delete(id);
        } catch (Exception e) {
            throw new NotFoundException("Возникла ошибка при удалении игры с id=" + id);
        }

//        WelcomeDto welcomeDto = userService.getWelcome(cache.getLogin());

 //       model.addAttribute("welcome", welcomeDto);

        return "redirect:/welcome?login=" + cache.getLogin();

    }

    // игра для заданного идентификатора
    @GetMapping("/api/games/{id}")
    public GameDto getOne(@PathVariable Long id) {
        var game = gameService.getOne(id);
        log.info(game.toString());
        return game;
    }

    // выполнить ход
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String doStuffMethod2(Model model,
                                 @ModelAttribute("game") GameDto gameDto,
                                 @ModelAttribute("xys") InputXYDTO inputXYDTO) {

        log.info("Зашли с gameDto: " + gameDto.toString());

        var game = gameService.getOne(gameId);
        log.info(game.toString());

        // todo под вопросом причины пустого gameDto, хотя передается inputXYDTO
        gameService.doStep(game, inputXYDTO);

        game = gameService.getOne(gameId);
        log.info(game.toString());

        model.addAttribute("game", game);
        model.addAttribute("xys", inputXYDTO);

//        System.out.println("gameDto + " + gameDto);
        System.out.println("inputXYDTO + " + inputXYDTO);
        return "list";
    }

}

