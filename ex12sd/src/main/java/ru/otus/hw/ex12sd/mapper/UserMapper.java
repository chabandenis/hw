package ru.otus.hw.ex12sd.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex12sd.dto.UserDto;
import ru.otus.hw.ex12sd.dto.user.UserCreateDto;
import ru.otus.hw.ex12sd.dto.user.UserUpdateDto;
import ru.otus.hw.ex12sd.models.User;

@Component
public class UserMapper {

    public static User toUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLogin(),
                userDto.getPassword()
        );
        return user;
    }

    public static UserDto toUserAllDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
        return userDto;
    }

    public static User toUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setLogin(userCreateDto.getLogin());
        user.setPassword(userCreateDto.getPassword());
        return user;
    }

    public static UserDto toUserDto(Long userId, UserUpdateDto user) {
        UserDto userAllDto = new UserDto(
                userId,
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
        return userAllDto;
    }

    public static UserDto toUserDto(User user) {
        UserDto userAllDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
        return userAllDto;
    }

    public static UserDto toUserCreatedDto(Long userId, UserCreateDto user) {
        UserDto userAllDto = new UserDto(
                userId,
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
        return userAllDto;
    }
}