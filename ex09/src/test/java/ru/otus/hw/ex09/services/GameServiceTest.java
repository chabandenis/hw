package ru.otus.hw.ex09.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex09.dto.InputXYDTO;
import ru.otus.hw.ex09.mapper.ChessFairMapper;
import ru.otus.hw.ex09.mapper.FiguraMapper;
import ru.otus.hw.ex09.mapper.GameMapper;
import ru.otus.hw.ex09.mapper.ImputXYMapper;
import ru.otus.hw.ex09.mapper.PositionInChessFairMapper;
import ru.otus.hw.ex09.mapper.UserMapper;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Autowired
    private GameMapper gameMapper;

    @Test
    void doStep() {

        var game = gameService.getOne(1L);

        InputXYDTO inputXYDTO = new InputXYDTO();
        inputXYDTO.setXFirst("A");
        inputXYDTO.setYFirst("3");
        inputXYDTO.setXSecond("A");
        inputXYDTO.setYSecond("4");

        System.out.println(game.getChessFair().getDesk().get(0));
        System.out.println(game.getChessFair().getDesk().get(1));
        System.out.println(game.getChessFair().getDesk().get(2));
        System.out.println(game.getChessFair().getDesk().get(3));
        System.out.println(game.getChessFair().getDesk().get(4));
        System.out.println(game.getChessFair().getDesk().get(5));
        System.out.println(game.getChessFair().getDesk().get(6));
        System.out.println(game.getChessFair().getDesk().get(7));

        gameService.doStep(game, inputXYDTO);

        var gameAfterStep = gameService.getOne(1L);

        System.out.println("исходная точка было " +
                game.getChessFair().getDesk().get(5).getArr().get(0).getVal());
        assertThat(game.getChessFair().getDesk().get(5).getArr().get(0).getVal()).isEqualTo("O");

        System.out.println("исходная точка стало " +
                gameAfterStep.getChessFair().getDesk().get(5).getArr().get(0).getVal());
        assertThat(gameAfterStep.getChessFair().getDesk().get(5).getArr().get(0).getVal()).isEqualTo("");

        System.out.println(gameAfterStep.getChessFair().getDesk().get(0));
        System.out.println(gameAfterStep.getChessFair().getDesk().get(1));
        System.out.println(gameAfterStep.getChessFair().getDesk().get(2));
        System.out.println(gameAfterStep.getChessFair().getDesk().get(3));
        System.out.println(gameAfterStep.getChessFair().getDesk().get(4));
        System.out.println(gameAfterStep.getChessFair().getDesk().get(5));
        System.out.println(gameAfterStep.getChessFair().getDesk().get(6));
        System.out.println(gameAfterStep.getChessFair().getDesk().get(7));

        System.out.println("Конечная точка было " +
                game.getChessFair().getDesk().get(4).getArr().get(0).getVal());
        assertThat(game.getChessFair().getDesk().get(4).getArr().get(0).getVal()).isEqualTo("");

        System.out.println("Конечная точка стало " +
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