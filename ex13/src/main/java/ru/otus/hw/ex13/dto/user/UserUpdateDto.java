package ru.otus.hw.ex12_r_hw.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex12_r_hw.models.User;

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
}