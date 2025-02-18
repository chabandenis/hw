package ru.otus.hw.ex12_r_hw.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex12_r_hw.container.BaseContainerTest;
import ru.otus.hw.ex12_r_hw.dto.GameDto;
import ru.otus.hw.ex12_r_hw.dto.game.CoordinatesDto;
import ru.otus.hw.ex12_r_hw.dto.game.GamesCreateDto;
import ru.otus.hw.ex12_r_hw.repositories.game.GameRepositoryCustom;
import ru.otus.hw.ex12_r_hw.services.Game.GameService;
import ru.otus.hw.ex12_r_hw.services.Game.GameServiceCreate;
import ru.otus.hw.ex12_r_hw.services.Game.GameServiceGetOne;
import ru.otus.hw.ex12_r_hw.services.Game.GameServiceStep;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Test class for the {@link GameController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTest extends BaseContainerTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private GameController gameController;

    @MockBean
    private GameServiceCreate gameServiceCreate;


    @MockBean
    private GameServiceGetOne gameServiceGetOne;

    @MockBean
    private GameServiceStep gameServiceStep;

    @MockBean
    private GameRepositoryCustom gameRepositoryCustom;

    @Test
    public void test() {

    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testDelete() {
        Long userId = 4L;

        when(gameService.delete(userId)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/game/" + userId)
                .exchange()
                .expectStatus().isNoContent();

        verify(gameService).delete(userId);

    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testGetGamesForUsers() {
        Long mainUser = 1L;
        Long secondUser = 2L;
        List<GameDto> gameDtos = List.of(new GameDto(), new GameDto());

        when(gameRepositoryCustom.findAll(mainUser, secondUser)).thenReturn(Flux.fromIterable(gameDtos));

        webTestClient.get()
                .uri("/api/game/{mainUser}/{secondUser}", mainUser, secondUser)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GameDto.class)
                .hasSize(2);
    }


    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testStep() {
        Long gameId = 1L;
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setX1("D");
        coordinatesDto.setY1("1");
        coordinatesDto.setX2("F");
        coordinatesDto.setY2("1");

        GameDto gameDto = new GameDto();
        gameDto.setId(gameId);

        when(gameServiceStep.step(any(), any())).thenReturn(Mono.just(ResponseEntity.ok(gameDto)));

        webTestClient.put()
                .uri("/api/game/{gameId}", gameId)
                .contentType(APPLICATION_JSON)
                .bodyValue(coordinatesDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(GameDto.class)
                .isEqualTo(gameDto);
    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testCreate() {
        GamesCreateDto gamesCreateDto = new GamesCreateDto();
        gamesCreateDto.setMainUser(1L);
        gamesCreateDto.setSecondUser(2L);

        GameDto gameDto = new GameDto();
        gameDto.setId(1L);

        when(gameServiceCreate.create(gamesCreateDto)).thenReturn(Mono.just(ResponseEntity.ok(gameDto)));

        webTestClient.post()
                .uri("/api/game")
                .contentType(APPLICATION_JSON)
                .bodyValue(gamesCreateDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(GameDto.class)
                .isEqualTo(gameDto);
    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testGetOne() {
        Long gameId = 1L;
        GameDto gameDto = new GameDto();
        gameDto.setId(gameId);

        when(gameServiceGetOne.getOne(gameId)).thenReturn(Mono.just(ResponseEntity.ok(gameDto)));

        webTestClient.get()
                .uri("/api/game/{id}", gameId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(GameDto.class)
                .isEqualTo(gameDto);
    }


}
