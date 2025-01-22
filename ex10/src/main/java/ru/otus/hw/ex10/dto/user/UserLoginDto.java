package ru.otus.hw.ex10.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginDto {

    @Length(min = 3)
    private String login;

    @Length(min = 1)
    private String password;
}