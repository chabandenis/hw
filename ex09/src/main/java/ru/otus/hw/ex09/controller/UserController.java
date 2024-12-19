package ru.otus.hw.ex09.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw.ex09.logic.Cache;
import ru.otus.hw.ex09.services.UserService;
import ru.otus.hw.ex09.web.LoginDto;
import ru.otus.hw.ex09.web.WelcomeDto;

@Controller
@AllArgsConstructor
@Getter
public class UserController {

    private final UserService userService;

    private final Cache cache;

//    private final Login login = new Login();
//    private final Welcome welcome = new Welcome();

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

