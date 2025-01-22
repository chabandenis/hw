package ru.otus.hw.ex10.dto.user;

import lombok.Data;

@Data
public class UserLoginDto {

    private String login;

    private String password;
}