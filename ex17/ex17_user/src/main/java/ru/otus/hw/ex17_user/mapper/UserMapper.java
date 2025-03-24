package ru.otus.hw.ex17_user.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex17_user.dto.UserDto;
import ru.otus.hw.ex17_user.dto.user.UserCreateDto;
import ru.otus.hw.ex17_user.dto.user.UserUpdateDto;
import ru.otus.hw.ex17_user.models.User;

@Component
public class UserMapper {

    public static UserDto toUserAllDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getRole()
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
                user.getPassword(),
                user.getRole()
        );
        return userAllDto;
    }

    public static UserDto toUserDto(User user) {
        UserDto userAllDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getRole()
        );
        return userAllDto;
    }

    public static UserDto toUserCreatedDto(Long userId, UserCreateDto user) {
        UserDto userAllDto = new UserDto(
                userId,
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getRole()
        );
        return userAllDto;
    }
}