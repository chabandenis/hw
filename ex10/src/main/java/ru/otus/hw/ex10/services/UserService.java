package ru.otus.hw.ex10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex10.controller.NotFoundException;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.dto.fromWeb.UserLoginActionDto;
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
    public UserDto findByLogin(UserLoginActionDto userLoginActionDto) {
        UserDto user = userRepository.findByLoginAndPassword(
                        userLoginActionDto.getLogin(),
                        userLoginActionDto.getPassword())
                .map(UserMapper::toUserDto)
                .orElseThrow(() ->
                        new NotFoundException("Ошибка авторизации для пользователя " + userLoginActionDto.getLogin()));
        return user;
    }

    @Transactional
    public UserDto insert(User user) {
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Transactional
    public UserDto update(User user) {
        var userUpdated = userRepository.findById(user.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Отсутствует пользователь с идентификатором id="
                                + user.getId()
                        )
                );

        userUpdated.setName(user.getName());
        userUpdated.setLogin(user.getLogin());
        userUpdated.setPassword(user.getPassword());

        return UserMapper.toUserDto(userRepository.save(userUpdated));
    }

    @Transactional
    public UserDto delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            gameService.deleteByUser(id);
            userRepository.delete(user);
        }
        return UserMapper.toUserDto(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(x -> UserMapper.toUserDto(x))
                .collect(Collectors.toList());
    }

}
