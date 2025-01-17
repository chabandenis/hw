package ru.otus.hw.ex11.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({
        GameService.class,
        InputXYService.class
})
class GameServiceTest {
    @Autowired
    private GameService gameService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void newGame() {
    }

    @Test
    void getOne() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteByUser() {
    }

    @Test
    void getAllByUsers() {
    }
}