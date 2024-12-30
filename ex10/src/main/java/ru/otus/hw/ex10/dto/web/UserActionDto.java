package ru.otus.hw.ex10.dto.web;

import lombok.Data;

@Data
public class UserActionDto {

    private String action;

    private String login;

    private String password;
}