package ru.otus.hw.ex09.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex09.dto.UserDto;
import ru.otus.hw.ex09.models.User;

@Component
public class UserMapper {
//    User toEntity(UserDto userDto);

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName()
        );
        return userDto;
    }

    ;

}