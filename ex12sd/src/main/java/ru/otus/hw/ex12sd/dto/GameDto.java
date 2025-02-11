package ru.otus.hw.ex12sd.dto;

import lombok.Data;
import ru.otus.hw.ex12sd.models.Game;

import java.time.LocalDateTime;

/**
 * DTO for {@link Game}
 */
@Data
public class GameDto {
    private Long id;

    private UserDto userBlack;

    private UserDto userWhite;

    private UserDto userNext;

    private ChessFairDto chessFair;

    private LocalDateTime dateGame;
}