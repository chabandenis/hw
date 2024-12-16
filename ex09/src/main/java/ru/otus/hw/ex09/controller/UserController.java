package ru.otus.hw.ex09.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.hw.ex09.models.User;
import ru.otus.hw.ex09.services.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
//@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public @ResponseBody List<User> getAll() {
        return userService.getAll();
    }
}

