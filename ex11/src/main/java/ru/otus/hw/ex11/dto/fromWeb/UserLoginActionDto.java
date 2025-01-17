package ru.otus.hw.ex11.dto.fromWeb;

import lombok.Data;

@Data
public class UserLoginActionDto {

    private String login;

    private String password;
}