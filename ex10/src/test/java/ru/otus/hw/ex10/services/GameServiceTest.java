package ru.otus.hw.ex10.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex10.dto.InputXYDTO;
import ru.otus.hw.ex10.mapper.ChessFairMapper;
import ru.otus.hw.ex10.mapper.FiguraMapper;
import ru.otus.hw.ex10.mapper.GameMapper;
import ru.otus.hw.ex10.mapper.ImputXYMapper;
import ru.otus.hw.ex10.mapper.PositionInChessFairMapper;
import ru.otus.hw.ex10.mapper.UserMapper;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({
        GameService.class,
        GameMapper.class,
        UserMapper.class,
        ChessFairMapper.class,
        PositionInChessFairMapper.class,
        FiguraMapper.class,
        InputXYService.class,
        ImputXYMapper.class
})
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    void doStep() {

        var game = gameService.getOne(1L);

        InputXYDTO inputXYDTO = new InputXYDTO();
        inputXYDTO.setX1("A");
        inputXYDTO.setY1("3");
        inputXYDTO.setX2("A");
        inputXYDTO.setY2("4");

        log.debug(game.getChessFair().getDesk().get(0).toString());
        log.debug(game.getChessFair().getDesk().get(1).toString());
        log.debug(game.getChessFair().getDesk().get(2).toString());
        log.debug(game.getChessFair().getDesk().get(3).toString());
        log.debug(game.getChessFair().getDesk().get(4).toString());
        log.debug(game.getChessFair().getDesk().get(5).toString());
        log.debug(game.getChessFair().getDesk().get(6).toString());
        log.debug(game.getChessFair().getDesk().get(7).toString());

        gameService.doStep(game, inputXYDTO);

        var gameAfterStep = gameService.getOne(1L);

        log.debug("исходная точка было " +
                game.getChessFair().getDesk().get(5).getArr().get(0).getVal());
        assertThat(game.getChessFair().getDesk().get(5).getArr().get(0).getVal()).isEqualTo("O");

        log.debug("исходная точка стало " +
                gameAfterStep.getChessFair().getDesk().get(5).getArr().get(0).getVal());
        assertThat(gameAfterStep.getChessFair().getDesk().get(5).getArr().get(0).getVal()).isEqualTo("");

        log.debug(gameAfterStep.getChessFair().getDesk().get(0).toString());
        log.debug(gameAfterStep.getChessFair().getDesk().get(1).toString());
        log.debug(gameAfterStep.getChessFair().getDesk().get(2).toString());
        log.debug(gameAfterStep.getChessFair().getDesk().get(3).toString());
        log.debug(gameAfterStep.getChessFair().getDesk().get(4).toString());
        log.debug(gameAfterStep.getChessFair().getDesk().get(5).toString());
        log.debug(gameAfterStep.getChessFair().getDesk().get(6).toString());
        log.debug(gameAfterStep.getChessFair().getDesk().get(7).toString());

        log.debug("Конечная точка было " +
                game.getChessFair().getDesk().get(4).getArr().get(0).getVal());
        assertThat(game.getChessFair().getDesk().get(4).getArr().get(0).getVal()).isEqualTo("");

        log.debug("Конечная точка стало " +
                gameAfterStep.getChessFair().getDesk().get(4).getArr().get(0).getVal());
        assertThat(gameAfterStep.getChessFair().getDesk().get(4).getArr().get(0).getVal()).isEqualTo("O");

    }

    @Test
    void getOne() {
        long idExpected = 1l;
        var game = gameService.getOne(idExpected);
        assertThat(game).isNotNull();

        assertThat(game.getId()).isEqualTo(idExpected);
    }
}