package ru.otus.hw.ex10.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex10.controller.NotFoundException;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.mapper.UserMapper;
import ru.otus.hw.ex10.repositories.GameRepository;
import ru.otus.hw.ex10.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public UserDto findUserByLogin(String login, String password) {
        UserDto user = userRepository.findByLogin(login)
                .map(UserMapper::toUserDto)
                .orElseThrow(() ->
                        new NotFoundException("Ошибка авторизации для пользователя " + login));
        return user;
    }


/*    public WelcomeDto getWelcome(String login) {
        WelcomeDto welcomeDto = new WelcomeDto();

        String errorText = "Зарегистрируйтесь. Отсутствует пользователь с логином \"" + login + "\"";
        UserDto user = userRepository.findByLogin(login)
                .map(UserMapper::toUserDto)
                .orElseThrow(() ->
                        new NotFoundException(errorText));


//        List<Game> games = gameRepository.findByUserWhiteId(user.getId());
//        games.addAll(gameRepository.findByUserBlackId(user.getId()));

        // отсортированный список с выборкой по двум сущностям
        List<Game> games = gameRepository.findByUserBlackIdOrUserWhiteIdOrderByIdDesc(user.getId());

        welcomeDto.setGames(games.stream().map(GameMapper::toGameDto).toList());

        welcomeDto.setName(user.getName());

        return welcomeDto;
    }

 */
}
