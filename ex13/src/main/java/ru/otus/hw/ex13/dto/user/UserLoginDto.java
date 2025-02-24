package ru.otus.hw.ex13.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class UserLoginDto {

    @Length(min = 3)
    private String login;

    @Length(min = 1)
    private String password;
}