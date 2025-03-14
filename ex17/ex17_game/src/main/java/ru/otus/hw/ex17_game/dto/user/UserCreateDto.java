package ru.otus.hw.ex17_game.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.hw.ex17_game.models.User;

/**
 * DTO for {@link User}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @NotEmpty
    private String name;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String role;
}