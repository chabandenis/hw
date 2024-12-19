package ru.otus.hw.ex09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex09.controller.NotFoundException;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.dto.UserDto;
import ru.otus.hw.ex09.mapper.GameMapper;
import ru.otus.hw.ex09.mapper.UserMapper;
import ru.otus.hw.ex09.models.Game;
import ru.otus.hw.ex09.repositories.GameRepository;
import ru.otus.hw.ex09.repositories.UserRepository;
import ru.otus.hw.ex09.web.WelcomeDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;


    public WelcomeDto getWelcome(String login) throws Exception {
        WelcomeDto welcomeDto = new WelcomeDto();

        String errorText = "Зарегистрируйтесь. Отсутствует пользователь с логином \"" + login + "\"";
        UserDto user =  userRepository.findByLogin(login)
                .map(UserMapper::toUserDto)
                .orElseThrow(()->
                        new NotFoundException(errorText));


        List<Game> games = gameRepository.findByUserWhiteId(user.getId());
        games.addAll(gameRepository.findByUserBlackId(user.getId()));

        welcomeDto.setGames(games.stream().map(GameMapper::toGameDto).toList());

        welcomeDto.setName(user.getName());

        return welcomeDto;
    }
}
