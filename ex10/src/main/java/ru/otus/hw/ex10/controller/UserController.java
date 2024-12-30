package ru.otus.hw.ex10.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.dto.web.UserActionDto;
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
    @PostMapping(value = "/api/users/actions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody UserActionDto userActionDto) throws Exception {
        var user = userService.findUserByLogin(userActionDto);
        return user;
    }
}

