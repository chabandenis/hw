package ru.otus.hw.ex09.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.hw.ex09.dto.UserDto;
import ru.otus.hw.ex09.mapper.UserMapper;
import ru.otus.hw.ex09.models.User;
import ru.otus.hw.ex09.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/")
//@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @GetMapping
    public @ResponseBody List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
}

