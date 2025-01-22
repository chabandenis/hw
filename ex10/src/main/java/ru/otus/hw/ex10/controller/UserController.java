package ru.otus.hw.ex10.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.dto.user.UserCreateDto;
import ru.otus.hw.ex10.dto.user.UserLoginDto;
import ru.otus.hw.ex10.dto.user.UserUpdateDto;
import ru.otus.hw.ex10.services.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // пользователи
    // http://localhost:8080/api/user
    @GetMapping("")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    // авторизация пользователя
    // http://localhost:8080/api/user/login
    // {"login": "user1", "password": "1"}
    @PutMapping(value = "/login")
    public UserDto login(@RequestBody @Valid UserLoginDto userLoginDto) throws Exception {
        return userService.findByLogin(userLoginDto);
    }

    // создать пользователя
    // http://localhost:8080/api/user
    // {"name":"user5", "login":"login", "password":"1" }
    @PostMapping(value = "")
    public UserDto create(@RequestBody @Valid UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    // обновить пользователя
    // http://localhost:8080/api/user/1
    // {"name":"Первый Иван Иваныч Иванов", "login":"login", "password":"1"}
    @PutMapping(value = "/{userId}")
    public UserDto put(@PathVariable Long userId,
                       @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return userService.put(userId, userUpdateDto);

    }

    // удалить пользователя
    // http://localhost:8080/api/user/4
    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}