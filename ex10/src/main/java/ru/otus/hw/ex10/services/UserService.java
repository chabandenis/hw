package ru.otus.hw.ex10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex10.controller.NotFoundException;
import ru.otus.hw.ex10.dto.user.UserResultDto;
import ru.otus.hw.ex10.dto.user.UserCreateDto;
import ru.otus.hw.ex10.dto.user.UserLoginDto;
import ru.otus.hw.ex10.dto.user.UserUpdateDto;
import ru.otus.hw.ex10.mapper.UserMapper;
import ru.otus.hw.ex10.models.User;
import ru.otus.hw.ex10.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final GameService gameService;

    @Transactional(readOnly = true)
    public UserResultDto findByLogin(UserLoginDto userLoginDto) {
        UserResultDto user = userRepository.findByLoginAndPassword(
                        userLoginDto.getLogin(),
                        userLoginDto.getPassword())
                .map(UserMapper::toUserAllDto)
                .orElseThrow(() ->
                        new NotFoundException("Ошибка авторизации для пользователя <" +
                                userLoginDto.getLogin() + ">"));
        return user;
    }

    @Transactional
    public UserResultDto create(UserCreateDto userCreateDto) {
        var userInDb = userRepository.findByLogin(userCreateDto.getLogin());

        if (userInDb.isPresent()) {
            throw new EntityNotFoundException("Пользователь с логином " + userCreateDto.getLogin()
                    + " был зарегистрирован в системе ранее");
        }

        return UserMapper.toUserAllDto(userRepository.save(UserMapper.toUser(userCreateDto)));
    }

    @Transactional
    public UserResultDto put(Long userId, UserUpdateDto userUpdateDto) {
        var userUpdated = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Отсутствует пользователь с идентификатором id="
                                + userId
                        )
                );

        userUpdated.setName(userUpdateDto.getName());
        userUpdated.setLogin(userUpdateDto.getLogin());
        userUpdated.setPassword(userUpdateDto.getPassword());

        return UserMapper.toUserAllDto(userRepository.save(userUpdated));
    }

    @Transactional
    public UserResultDto delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            gameService.deleteByUser(id);
            userRepository.delete(user);
        }
        return UserMapper.toUserAllDto(user);
    }

    public List<UserResultDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(x -> UserMapper.toUserAllDto(x))
                .collect(Collectors.toList());
    }

}
