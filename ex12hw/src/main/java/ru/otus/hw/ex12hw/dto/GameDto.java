package ru.otus.hw.ex12hw.dto;

import lombok.Data;
import ru.otus.hw.ex12hw.models.Game;

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