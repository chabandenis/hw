package ru.otus.hw.ex10.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.models.User;

@Component
public class UserMapper {
//    User toEntity(UserDto userDto);

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
        return userDto;
    }
}