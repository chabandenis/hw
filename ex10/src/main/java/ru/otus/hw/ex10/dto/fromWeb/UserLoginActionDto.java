package ru.otus.hw.ex10.dto.fromWeb;

import lombok.Data;

@Data
public class UserLoginActionDto {

    private String login;

    private String password;
}