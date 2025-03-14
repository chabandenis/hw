package ru.otus.hw.ex17_user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_user.config.ApplConfig;
import ru.otus.hw.ex17_user.dto.UserDto;
import ru.otus.hw.ex17_user.dto.user.UserCreateDto;
import ru.otus.hw.ex17_user.dto.user.UserLoginDto;
import ru.otus.hw.ex17_user.dto.user.UserUpdateDto;
import ru.otus.hw.ex17_user.models.Game;
import ru.otus.hw.ex17_user.models.User;
import ru.otus.hw.ex17_user.repositories.UserRepository;
import ru.otus.hw.ex17_user.repositories.GameRepository;
import ru.otus.hw.ex17_user.security.CustomReactiveUserDetailsService;
import ru.otus.hw.ex17_user.security.SecurityConfiguration;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

/**
 * Test class for the {@link UserController}
 */
@WebFluxTest({UserController.class})
@Import({ApplConfig.class,
        SecurityConfiguration.class,
        CustomReactiveUserDetailsService.class})
public class UserControllerWebTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void verif() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        User user1 = new User(1L, "Aa", "BB", "CC", "USER");
        User user2 = new User(2L, "dd", "ee", "ff", "USER");

        when(userRepository.findAll()).thenReturn(Flux.just(user1, user2));

        webTestClient
                .get().uri("/api/user")
                .exchange()
                .expectStatus().isUnauthorized();

        webTestClient
                .mutateWith(mockUser())
                .get().uri("/api/user")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void login() throws Exception {
        when(userRepository.findByLoginAndPassword(any(), any()))
                .thenReturn(Mono.empty());
        UserLoginDto userLoginDto = new UserLoginDto("user1", "1");

        webTestClient.put()
                .uri("/api/user/login")
                .bodyValue(userLoginDto)
                .exchange()
                .expectStatus().isUnauthorized();

        webTestClient
                .mutateWith(mockUser())
                .put()
                .uri("/api/user/login")
                .bodyValue(userLoginDto)
                .exchange()
                .expectStatus().isOk();
    }

    // аутентификация не требуется
    @Test
    public void create() throws Exception {
        User user = new User(1l, "user5", "login", "1", "USER");
        UserCreateDto userCreateDto = new UserCreateDto("user5", "login", "1", "USER");
        UserDto expectedUserDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getRole());

        when(userRepository.save(any(User.class))).thenReturn(Mono.just(user));

        // Выполняем POST запрос на создание пользователя
        webTestClient.post()
                .uri("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userCreateDto), UserCreateDto.class)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    public void put() throws Exception {
        Long userId = 1L;
        UserUpdateDto userUpdateDto = new UserUpdateDto(
                "Первый Иван Иваныч Иванов",
                "login",
                "1",
                "USER");
        UserDto expectedUserDto = new UserDto(
                userId,
                "Первый Иван Иваныч Иванов",
                "login",
                "1",
                "USER");
        User expectedUser = new User(
                userId,
                "Первый Иван Иваныч Иванов",
                "login",
                "1",
                "USER");

        when(userRepository.findById(userId))
                .thenReturn(Mono.just(expectedUser));

        when(userRepository.save(any()))
                .thenReturn(Mono.just(expectedUser));

        // Выполняем PUT запрос на обновление пользователя
        webTestClient
                .put()
                .uri("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userUpdateDto), UserUpdateDto.class)
                .exchange()
                .expectStatus().isUnauthorized();

        // Выполняем PUT запрос на обновление пользователя
        webTestClient
                .mutateWith(mockUser())
                .put()
                .uri("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userUpdateDto), UserUpdateDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void delete() throws Exception {
        Flux<Game> expectedGames = Flux.just(
                new Game(1l, 1L, 1L, 4L, 1l, LocalDateTime.now()),
                new Game(2l, 2L, 2L, 5L, 2l, LocalDateTime.now()),
                new Game(3l, 3L, 3L, 6L, 3l, LocalDateTime.now())
        );

        when(gameRepository.findByUserWhiteIdInAndUserBlackIdInOrderByDateGameDesc(
                anyCollection(), anyCollection()))
                .thenReturn(expectedGames);

        when(userRepository.deleteById(any(Long.class))).thenReturn(Mono.empty());

        // разрешение на удаление под пользователем с правами ADMIN
        webTestClient
                .mutateWith(mockUser().authorities("SCOPE_ROLE_ADMIN"))
                .delete()
                .uri("/api/user/4")
                .exchange()
                .expectStatus().isNoContent();

        // запрет на удаление пользователя для роли USER
        webTestClient
                .mutateWith(mockUser().roles("USER"))
                .delete()
                .uri("/api/user/4")
                .exchange()
                .expectStatus().isForbidden();

        webTestClient
                .delete()
                .uri("/api/user/4")
                .exchange()
                .expectStatus().isUnauthorized();

    }
}
