package ru.otus.hw.ex14.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.otus.hw.ex14.config.ApplConfig;
import ru.otus.hw.ex14.dto.game.CoordinatesDto;
import ru.otus.hw.ex14.dto.game.GamesCreateDto;
import ru.otus.hw.ex14.repositories.UserRepository;
import ru.otus.hw.ex14.repositories.game.GameRepositoryCustom;
import ru.otus.hw.ex14.security.CustomReactiveUserDetailsService;
import ru.otus.hw.ex14.security.SecurityConfiguration;
import ru.otus.hw.ex14.services.Game.GameService;
import ru.otus.hw.ex14.services.Game.GameServiceCreate;
import ru.otus.hw.ex14.services.Game.GameServiceGetOne;
import ru.otus.hw.ex14.services.Game.GameServiceStep;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

/**
 * Test class for the {@link GameController}
 */
@WebFluxTest({GameController.class})
@Import({ApplConfig.class,
        SecurityConfiguration.class,
        CustomReactiveUserDetailsService.class})
public class GameControllerWebTest {

    @MockBean
    private GameServiceCreate gameServiceCreate;

    @MockBean
    private GameService gameService;

    @MockBean
    private GameServiceGetOne gameServiceGetOne;

    @MockBean
    private GameServiceStep gameServiceStep;

    @MockBean
    private GameRepositoryCustom gameRepositoryCustom;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void verif() throws Exception {
    }

    @Test
    public void getGamesForUsers() throws Exception {
        Long mainUser = 1L;
        Long secondUser = 2L;

        webTestClient
                .get()
                .uri("/api/game/{mainUser}/{secondUser}", mainUser, secondUser)
                .exchange()
                .expectStatus().isUnauthorized();

        webTestClient
                .mutateWith(mockUser())
                .get()
                .uri("/api/game/{mainUser}/{secondUser}", mainUser, secondUser)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void step() throws Exception {
        Long gameId = 1L;
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setX1("D");
        coordinatesDto.setY1("1");
        coordinatesDto.setX2("F");
        coordinatesDto.setY2("1");

        webTestClient
                .put()
                .uri("/api/game/{gameId}", gameId)
                .bodyValue(coordinatesDto)
                .exchange()
                .expectStatus().isUnauthorized();

        webTestClient
                .mutateWith(mockUser())
                .put()
                .uri("/api/game/{gameId}", gameId)
                .bodyValue(coordinatesDto)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void create() throws Exception {
        GamesCreateDto gamesCreateDto = new GamesCreateDto();
        gamesCreateDto.setMainUser(1L);
        gamesCreateDto.setSecondUser(2L);

        webTestClient
                .mutateWith(mockUser())
                .post()
                .uri("/api/game")
                .bodyValue(gamesCreateDto)
                .exchange()
                .expectStatus().isOk();

        webTestClient
                .post()
                .uri("/api/game")
                .bodyValue(gamesCreateDto)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void delete() throws Exception {
        Long id = 7L;

        webTestClient
                .delete()
                .uri("/api/game/{id}", id)
                .exchange()
                .expectStatus().isUnauthorized();

        // разрешение удаление под админом
        webTestClient
                .mutateWith(mockUser().authorities("SCOPE_ROLE_ADMIN"))
                .delete()
                .uri("/api/game/{id}", id)
                .exchange()
                .expectStatus().isNoContent();

        // запрет уделения под пользователем
        webTestClient
                .mutateWith(mockUser().roles("USER"))
                .delete()
                .uri("/api/game/{id}", id)
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    public void getOne() throws Exception {
        Long id = 1L;

        webTestClient
                .get()
                .uri("/api/game/{id}", id)
                .exchange()
                .expectStatus().isUnauthorized();

        webTestClient
                .mutateWith(mockUser())
                .get()
                .uri("/api/game/{id}", id)
                .exchange()
                .expectStatus().isOk();

    }
}
