package ru.otus.hw.ex10.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex10.models.User;

/**
 * DTO for {@link User}
 */
@AllArgsConstructor
@Data
public class UserResultDto {

    private Long id;

    private String name;

    private String login;

}