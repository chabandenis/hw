package ru.otus.hw.ex17_front_game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/*
    Данные для создания игры
 */
public class GamesCreateDto {

    private Long mainUser;

    private Long secondUser;

}
