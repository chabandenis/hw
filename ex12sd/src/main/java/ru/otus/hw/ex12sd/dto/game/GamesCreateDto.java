package ru.otus.hw.ex12sd.dto.game;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
/*
    Данные для создания игры
 */
public class GamesCreateDto {

    @NotNull
    private Long mainUser;

    @NotNull
    private Long secondUser;

}
