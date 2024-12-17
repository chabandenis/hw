package ru.otus.hw.ex09.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

//    @Autowired
//    private GameMapper gameMapper;

    @Test
    void doStep() {
    }

    @Test
    void createEmptyDesk() {
    }

    @Test
    void fillFigureOnTheDesk() {
    }

    @Test
    void getOne() {
        long idExpected = 1l;
        var game = gameService.getOne(idExpected);
        assertThat(game).isNotNull();

        assertThat(game.getId()).isEqualTo(idExpected);
    }
}