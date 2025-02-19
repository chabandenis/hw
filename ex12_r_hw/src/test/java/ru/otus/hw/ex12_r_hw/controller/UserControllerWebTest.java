package ru.otus.hw.ex12_r_hw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex12_r_hw.config.ApplConfig;
import ru.otus.hw.ex12_r_hw.dto.UserDto;
import ru.otus.hw.ex12_r_hw.dto.user.UserCreateDto;
import ru.otus.hw.ex12_r_hw.dto.user.UserLoginDto;
import ru.otus.hw.ex12_r_hw.dto.user.UserUpdateDto;
import ru.otus.hw.ex12_r_hw.models.User;
import ru.otus.hw.ex12_r_hw.repositories.UserRepository;
import ru.otus.hw.ex12_r_hw.security.CustomReactiveUserDetailsService;
import ru.otus.hw.ex12_r_hw.security.MethodSecurityConfiguration;
import ru.otus.hw.ex12_r_hw.security.SecurityConfiguration;
import ru.otus.hw.ex12_r_hw.services.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

/**
 * Test class for the {@link UserController}
 */
@WebFluxTest({UserController.class})
@Import({ApplConfig.class,
        SecurityConfiguration.class,
        MethodSecurityConfiguration.class,
        CustomReactiveUserDetailsService.class})
public class UserControllerWebTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Autowired
    private Scheduler workerPool;

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
        User user1 = new User(1L, "Aa", "BB", "CC");
        User user2 = new User(2L, "dd", "ee", "ff");

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
        when(userService.findByLogin(any())).thenReturn(Mono.empty());
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
        UserCreateDto userCreateDto = new UserCreateDto("user5", "login", "1");

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
        UserUpdateDto userUpdateDto = new UserUpdateDto("Первый Иван Иваныч Иванов", "login", "1");

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

        webTestClient
                .mutateWith(mockUser())
                .delete()
                .uri("/api/user/4")
                .exchange()
                .expectStatus().isNoContent();

        webTestClient
                .delete()
                .uri("/api/user/4")
                .exchange()
                .expectStatus().isUnauthorized();

    }
}
