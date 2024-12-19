package ru.otus.hw.ex09.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.dto.InputXYDTO;
import ru.otus.hw.ex09.services.GameService;
import ru.otus.hw.ex09.services.InputXYService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    private final InputXYDTO inputXYDTO;

    private final InputXYService inputXYService;

    private Long gameId;

    // игра прописана в БД
    //http://localhost:8080/game/1
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

