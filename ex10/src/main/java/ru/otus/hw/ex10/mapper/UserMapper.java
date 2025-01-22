package ru.otus.hw.ex10.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.dto.user.UserResultDto;
import ru.otus.hw.ex10.dto.user.UserCreateDto;
import ru.otus.hw.ex10.models.User;

@Component
public class UserMapper {
//    User toEntity(UserDto userDto);

    public static UserResultDto toUserAllDto(User user) {
        UserResultDto userResultDto = new UserResultDto(
                user.getId(),
                user.getName(),
                user.getLogin()/*,
                user.getPassword()*/
        );
        return userResultDto;
    }

    public static User toUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setLogin(userCreateDto.getLogin());
        user.setPassword(userCreateDto.getPassword());
        return user;
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
}