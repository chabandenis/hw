package ru.otus.hw.ex11.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.dto.user.UserCreateDto;
import ru.otus.hw.ex11.dto.user.UserLoginDto;
import ru.otus.hw.ex11.dto.user.UserUpdateDto;
import ru.otus.hw.ex11.mapper.UserMapper;
import ru.otus.hw.ex11.repositories.UserRepository;
import ru.otus.hw.ex11.services.UserService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    private final Scheduler workerPool;

    // пользователи
    // http://localhost:8080/api/user
    @GetMapping("")
    public Flux<UserDto> getAll() {
        return userRepository.findAll().publishOn(workerPool).map(user-> UserMapper.toUserDto(user));
    }

    // авторизация пользователя
    // http://localhost:8080/api/user/login
    // {"login": "user1", "password": "1"}
    @PutMapping(value = "/login")
    public Mono<UserDto> login(@RequestBody @Valid UserLoginDto userLoginDto) throws Exception {
        return userService.findByLogin(userLoginDto);
    }

    // создать пользователя
    // http://localhost:8080/api/user
    // {"name":"user5", "login":"login", "password":"1" }
    @PostMapping(value = "")
    public Mono<ResponseEntity<UserDto>> create(@RequestBody /*@Valid*/ UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    // обновить пользователя
    // http://localhost:8080/api/user/1
    // {"name":"Первый Иван Иваныч Иванов", "login":"login", "password":"1"}
    @PutMapping(value = "/{userId}")
    public UserDto put(@PathVariable Long userId,
                       @RequestBody @Valid UserUpdateDto userUpdateDto) {
        //return userService.put(userId, userUpdateDto);
        return null;
    }

    // удалить пользователя
    // http://localhost:8080/api/user/4
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        //userService.delete(id);
    }
}