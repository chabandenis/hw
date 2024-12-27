package ru.otus.hw.ex10.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.dto.web.ActionDto;
import ru.otus.hw.ex10.logic.Cache;
import ru.otus.hw.ex10.services.UserService;

@RestController
@AllArgsConstructor
@Getter
public class UserController {

    private final UserService userService;

    private final Cache cache;

    /*
    http://localhost:8080/api/rest/users/action
    {
        "action": "login",
        "login": "user1",
        "password": "1"
    }
    */
    @PostMapping(value = "/api/rest/users/action", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody ActionDto actionDto) throws Exception {
        var user = userService.findUserByLogin(actionDto);
        return user;
    }
}

