package ru.otus.hw.ex12hw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex12hw.models.User;

/**
 * DTO for {@link User}
 */
@AllArgsConstructor
@Data
public class UserDto {

    private Long id;

    private String name;

    private String login;

    private String password;
}