package ru.otus.hw.ex09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex09.controller.NotFoundException;
import ru.otus.hw.ex09.dto.UserDto;
import ru.otus.hw.ex09.mapper.UserMapper;
import ru.otus.hw.ex09.repositories.UserRepository;
import ru.otus.hw.ex09.web.WelcomeDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
//    private final UserMapper userMapper;


    public WelcomeDto getWelcome(String login) throws Exception {
        WelcomeDto welcomeDto = new WelcomeDto();

        String errorText = "Зарегистрируйтесь. Отсутствует пользователь с логином \"" + login + "\"";
        UserDto user =  userRepository.findByLogin(login)
                .map(UserMapper::toUserDto)
                .orElseThrow(()->
                        new NotFoundException(errorText));


        welcomeDto.setName(user.getName());

        return welcomeDto;
    }
}
