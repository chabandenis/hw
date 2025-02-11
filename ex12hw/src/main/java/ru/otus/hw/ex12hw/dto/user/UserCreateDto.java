package ru.otus.hw.ex12hw.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.otus.hw.ex12hw.models.User;

/**
 * DTO for {@link User}
 */
@Data
public class UserCreateDto {

    @NotEmpty
    private String name;

    @NotNull
    private String login;

    @NotNull
    private String password;
}