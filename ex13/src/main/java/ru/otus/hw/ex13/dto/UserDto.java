package ru.otus.hw.ex13.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.ex13.models.User;

/**
 * DTO for {@link User}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;

    private String name;

    private String login;

    private String password;

    private String role;
}