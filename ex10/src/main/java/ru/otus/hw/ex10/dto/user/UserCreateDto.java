package ru.otus.hw.ex10.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.otus.hw.ex10.models.User;

/**
 * DTO for {@link User}
 */
@Data
public class UserCreateDto {

    @NotNull
    private String name;

    @NotNull
    private String login;

    @NotNull
    private String password;
}