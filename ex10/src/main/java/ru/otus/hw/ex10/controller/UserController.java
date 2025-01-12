package ru.otus.hw.ex10.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.dto.fromWeb.UserLoginActionDto;
import ru.otus.hw.ex10.models.User;
import ru.otus.hw.ex10.services.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@Getter
public class UserController {

    private final UserService userService;

    // пользователи
    @GetMapping("/api/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    // авторизация пользователя
    @PostMapping(value = "/api/users/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody UserLoginActionDto userLoginActionDto) throws Exception {
        return userService.findByLogin(userLoginActionDto);
    }

    // создать пользователя
    @PostMapping(value = "/api/users/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto insert(@RequestBody User user) {
        return userService.insert(user);
    }

    //обновить пользователя
    @PostMapping(value = "/api/users/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto update(@RequestBody User user) {
        return userService.update(user);
    }

    // удалить пользователя
    @PostMapping("/api/users/delete/{id}")
    public User delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}