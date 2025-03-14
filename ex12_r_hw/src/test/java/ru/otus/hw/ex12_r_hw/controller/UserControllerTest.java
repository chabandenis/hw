package ru.otus.hw.ex12_r_hw.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.hw.ex12_r_hw.config.ApplConfig;
import ru.otus.hw.ex12_r_hw.container.BaseContainerTest;
import ru.otus.hw.ex12_r_hw.dto.UserDto;
import ru.otus.hw.ex12_r_hw.dto.user.UserCreateDto;
import ru.otus.hw.ex12_r_hw.dto.user.UserLoginDto;
import ru.otus.hw.ex12_r_hw.dto.user.UserUpdateDto;
import ru.otus.hw.ex12_r_hw.models.Game;
import ru.otus.hw.ex12_r_hw.models.User;
import ru.otus.hw.ex12_r_hw.repositories.UserRepository;
import ru.otus.hw.ex12_r_hw.repositories.game.GameRepository;
import ru.otus.hw.ex12_r_hw.security.CustomReactiveUserDetailsService;
import ru.otus.hw.ex12_r_hw.security.MethodSecurityConfiguration;
import ru.otus.hw.ex12_r_hw.security.SecurityConfiguration;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Test class for the {@link UserController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({
        ApplConfig.class,
        SecurityConfiguration.class,
        MethodSecurityConfiguration.class,
        CustomReactiveUserDetailsService.class})
public class UserControllerTest extends BaseContainerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserController userController;

    @MockBean
    UserRepository userRepository;

    @MockBean
    GameRepository gameRepository;

    @Test
    public void verif() throws Exception {
    }

    @Test
    public void getAll() throws Exception {

        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setLogin("john.doe");
        user1.setPassword("password123");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Doe");
        user2.setLogin("jane.doe");
        user2.setPassword("password456");

        Flux<User> userFlux = Flux.just(user1, user2);
        when(userRepository.findAll()).thenReturn(userFlux);

        Flux<UserDto> userDtoFlux = userController.getAll();

        StepVerifier.create(userDtoFlux)
                .expectNext(new UserDto(1L, "John Doe", "john.doe", "password123"))
                .expectNext(new UserDto(2L, "Jane Doe", "jane.doe", "password456"))
                .verifyComplete();
    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void login() throws Exception {
        UserLoginDto userLoginDto = new UserLoginDto("user1", "1");
        UserDto userDto = new UserDto(1L, "John Doe", "user1", "1");

        when(userRepository.findByLoginAndPassword(any(), any()))
                .thenReturn(Mono.just(new User(1L, "John Doe", "user1", "1")));

        webTestClient.put().uri("/api/user/login")
                .contentType(APPLICATION_JSON)
                .bodyValue(userLoginDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class).isEqualTo(userDto);

    }

    @Test
    public void create() throws Exception {
        User user = new User(1l, "user5", "login", "1");
        UserCreateDto userCreateDto = new UserCreateDto("user5", "login", "1");
        UserDto expectedUserDto = new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword());

        when(userRepository.save(any(User.class))).thenReturn(Mono.just(user));

        webTestClient.post()
                .uri("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .isEqualTo(expectedUserDto);
    }

    @Test
    public void put() throws Exception {
        Long userId = 1L;
        UserUpdateDto userUpdateDto = new UserUpdateDto("Первый Иван Иваныч Иванов", "login", "1");
        UserDto expectedUserDto = new UserDto(userId, "Первый Иван Иваныч Иванов", "login", "1");
        User expectedUser = new User(userId, "Первый Иван Иваныч Иванов", "login", "1");

        when(userRepository.findById(userId))
                .thenReturn(Mono.just(expectedUser));

        when(userRepository.save(any()))
                .thenReturn(Mono.just(expectedUser));

        Mono<UserDto> result = userController.put(userId, userUpdateDto);

        StepVerifier.create(result)
                .expectNext(expectedUserDto)
                .verifyComplete();
    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void delete() throws Exception {
        Long userId = 4L;

        when(gameRepository.findByUserWhiteIdInAndUserBlackIdInOrderByDateGameDesc(
                anyCollection(), anyCollection()))
                .thenReturn(Flux.fromIterable(List.of(new Game())));

        when(userRepository.deleteById(userId)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/user/" + userId)
                .exchange()
                .expectStatus().isNoContent();

        //verify(userService).delete(userId);
    }
}