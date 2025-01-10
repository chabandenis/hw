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
import ru.otus.hw.ex10.logic.Cache;
import ru.otus.hw.ex10.models.User;
import ru.otus.hw.ex10.services.GameService;
import ru.otus.hw.ex10.services.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@Getter
public class UserController {

    private final UserService userService;

    private final GameService gameService;

    private final Cache cache;

    @GetMapping("/api/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping(value = "/api/users/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody UserLoginActionDto userLoginActionDto) throws Exception {
        return userService.findByLogin(userLoginActionDto);
    }

    @PostMapping(value = "/api/users/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @PostMapping(value = "/api/users/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/api/users/delete/{id}")
    public User delete(@PathVariable Long id) {
        return userService.delete(id);
    }

}

