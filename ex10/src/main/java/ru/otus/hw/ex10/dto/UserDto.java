package ru.otus.hw.ex10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex10.models.User;

/**
 * DTO for {@link User}
 */
@AllArgsConstructor
@Data
public class UserDto {

    private Long id;

    private String name;

    private String login;

//    private String password;
}