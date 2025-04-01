package ru.otus.hw.ex17_user.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex17_user.models.User;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
public class UserUpdateDto {
    @NotNull
    private String name;

    @NotNull
    private String login;

    @NotNull
    private String password;

    private String role;
}
