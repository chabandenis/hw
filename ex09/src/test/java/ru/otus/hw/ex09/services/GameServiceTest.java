package ru.otus.hw.ex09.services;

import org.junit.jupiter.api.Test;
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
    }
}