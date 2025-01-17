package ru.otus.hw.ex11.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex11.services.UserService;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

/*
    // пользователи
    @GetMapping("/api/users")
    public Flux<UserDto> getAll() {
        return userService.getAll();
    }

    // авторизация пользователя
    @PostMapping(value = "/api/users/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserDto> login(@RequestBody UserLoginActionDto userLoginActionDto) throws Exception {
        return userService.findByLogin(userLoginActionDto);
    }

    // создать пользователя
    @PostMapping(value = "/api/users/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserDto> insert(@RequestBody User user) {
        return userService.insert(user);
    }

    //обновить пользователя
    @PostMapping(value = "/api/users/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserDto> update(@RequestBody User user) {
        return userService.update(user);
    }

    // удалить пользователя
    @PostMapping("/api/users/delete/{id}")
    public Mono<UserDto> delete(@PathVariable Long id) {
        return userService.delete(id);
    }*/
}