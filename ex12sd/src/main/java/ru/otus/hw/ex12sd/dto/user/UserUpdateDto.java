package ru.otus.hw.ex12sd.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.otus.hw.ex12sd.models.User;

/**
 * DTO for {@link User}
 */
@Data
public class UserUpdateDto {
    @NotNull
    private String name;

    @NotNull
    private String login;

    @NotNull
    private String password;
}