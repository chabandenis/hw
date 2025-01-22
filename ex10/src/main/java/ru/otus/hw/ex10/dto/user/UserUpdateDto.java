package ru.otus.hw.ex10.dto.user;

import lombok.Data;
import ru.otus.hw.ex10.models.User;

/**
 * DTO for {@link User}
 */
@Data
public class UserUpdateDto {
    private String name;

    private String login;

    private String password;
}