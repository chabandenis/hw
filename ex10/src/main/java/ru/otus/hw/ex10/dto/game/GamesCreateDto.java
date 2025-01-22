package ru.otus.hw.ex10.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
/*
    Данные для создания игры
 */
public class GamesCreateDto {

    private Long mainUser;

    private Long secondUser;

}
