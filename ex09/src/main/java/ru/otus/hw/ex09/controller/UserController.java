package ru.otus.hw.ex09.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.hw.ex09.dto.UserDto;
import ru.otus.hw.ex09.mapper.UserMapper;
import ru.otus.hw.ex09.models.User;
import ru.otus.hw.ex09.repositories.UserRepository;

import java.util.List;

@Controller
@AllArgsConstructor
//@RequestMapping("/")
public class UserController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @GetMapping("/allUsers")
    public @ResponseBody List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserDto)
                .toList();
    }

}

