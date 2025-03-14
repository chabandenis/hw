package ru.otus.hw.ex17_game.dto.fromWeb;

import lombok.Data;

@Data
public class GameActionDto {

    private String action;

    private String userId;
}