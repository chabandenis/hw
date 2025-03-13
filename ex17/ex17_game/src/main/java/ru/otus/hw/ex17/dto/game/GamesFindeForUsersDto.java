package ru.otus.hw.ex17.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
/*
    пользователи в игре
 */
public class GamesFindeForUsersDto {
    private Long mainUser;

    private Long secondUser;

}
