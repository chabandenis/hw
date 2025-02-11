package ru.otus.hw.ex12sd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex12sd.models.User;

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