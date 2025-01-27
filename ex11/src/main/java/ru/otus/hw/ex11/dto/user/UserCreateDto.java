package ru.otus.hw.ex11.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.otus.hw.ex11.models.User;

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