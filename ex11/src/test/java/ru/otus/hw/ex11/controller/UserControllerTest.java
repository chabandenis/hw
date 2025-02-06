package ru.otus.hw.ex11.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.dto.user.UserCreateDto;
import ru.otus.hw.ex11.dto.user.UserLoginDto;
import ru.otus.hw.ex11.dto.user.UserUpdateDto;
import ru.otus.hw.ex11.models.User;
import ru.otus.hw.ex11.repositories.UserRepository;
import ru.otus.hw.ex11.services.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Test class for the {@link UserController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserController userController;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

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

    @Test
    public void login() throws Exception {
        UserLoginDto userLoginDto = new UserLoginDto("user1", "1");
        UserDto userDto = new UserDto(1L, "John Doe", "user1", "1");

        when(userService.findByLogin(userLoginDto)).thenReturn(Mono.just(userDto));

        webTestClient.put().uri("/api/user/login")
                .contentType(APPLICATION_JSON)
                .bodyValue(userLoginDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class).isEqualTo(userDto);

    }

    @Test
    public void create() throws Exception {
        // Arrange
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("user5");
        userCreateDto.setLogin("login");
        userCreateDto.setPassword("1");

        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setId(1L); // Assuming the service will assign an ID
        expectedUserDto.setName("user5");
        expectedUserDto.setLogin("login");
        expectedUserDto.setPassword("1");

        when(userService.create(userCreateDto)).thenReturn(Mono.just(ResponseEntity.ok(expectedUserDto)));

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

        when(userService.put(userId, userUpdateDto)).thenReturn(Mono.just(expectedUserDto));

        Mono<UserDto> result = userController.put(userId, userUpdateDto);

        StepVerifier.create(result)
                .expectNext(expectedUserDto)
                .verifyComplete();
    }

    @Test
    public void delete() throws Exception {
        Long userId = 4L;

        when(userService.delete(userId)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/user/" + userId)
                .exchange()
                .expectStatus().isNoContent();

        verify(userService).delete(userId);
    }
}