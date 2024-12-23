package ru.otus.hw.ex10.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw.ex10.logic.Cache;
import ru.otus.hw.ex10.services.UserService;
import ru.otus.hw.ex10.web.LoginDto;
import ru.otus.hw.ex10.web.WelcomeDto;

@Controller
@AllArgsConstructor
@Getter
public class UserController {

    private final UserService userService;

    private final Cache cache;

    // страница с логином
    @GetMapping("/")
    public String login(Model model) {
        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("user1");
        model.addAttribute("login", loginDto);
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam("login") String login, Model model) throws Exception {
        cache.setLogin(login);
        WelcomeDto welcomeDto = userService.getWelcome(login);

        model.addAttribute("welcome", welcomeDto);

        return "welcome";
    }


}

