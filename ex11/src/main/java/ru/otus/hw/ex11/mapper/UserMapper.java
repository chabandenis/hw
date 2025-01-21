package ru.otus.hw.ex11.mapper;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.models.User;

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

    public static Flux<UserDto> toUserDto(Flux<User> users) {
        return users.map(user -> {
            return new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getLogin(),
                    user.getPassword());
        });
    }
}