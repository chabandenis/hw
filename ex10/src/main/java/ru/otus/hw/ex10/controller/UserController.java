package ru.otus.hw.ex10.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.logic.Cache;
import ru.otus.hw.ex10.services.UserService;
import ru.otus.hw.ex10.web.WelcomeDto;

@RestController
@AllArgsConstructor
@Getter
public class UserController {

    private final UserService userService;

    private final Cache cache;

    // http://localhost:8080/rest/users/actions?login="user1"&password="1"
    @GetMapping("/rest/users/actions")
    public UserDto actions(
            @RequestParam("login") String login,
            @RequestParam("password") String password
    ) throws Exception {
        cache.setLogin(login);

        var user = userService.findUserByLogin(login, password);

        return user;
    }
}

