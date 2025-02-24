package ru.otus.hw.ex12_r_hw.dto.game;

import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long mainUser;

    @NotNull
    private Long secondUser;

}
