package ru.otus.hw.ex09.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.dto.InputXYDTO;
import ru.otus.hw.ex09.logic.Cache;
import ru.otus.hw.ex09.services.GameService;
import ru.otus.hw.ex09.services.UserService;
import ru.otus.hw.ex09.web.WelcomeDto;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    private final InputXYDTO inputXYDTO;

    private final Cache cache;

    private final UserService userService;


    private Long gameId;

    @GetMapping("/del")
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

        WelcomeDto welcomeDto = userService.getWelcome(cache.getLogin());

        model.addAttribute("welcome", welcomeDto);

        return "redirect:/welcome?login=" + cache.getLogin();

    }

    @GetMapping("/game")
    public String getOne(@RequestParam("id") Long id, Model model) {
        gameId = id;
        var game = gameService.getOne(id);
        log.info(game.toString());

        model.addAttribute("game", game);
        model.addAttribute("xys", inputXYDTO);
        return "list";
    }

    // выполнить ход
    @RequestMapping(value = "/do-stuff2", method = RequestMethod.POST)
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

