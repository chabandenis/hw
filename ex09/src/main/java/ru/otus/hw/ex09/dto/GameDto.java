package ru.otus.hw.ex09.dto;

import lombok.Value;
import ru.otus.hw.ex09.models.Game;

/**
 * DTO for {@link Game}
 */
@Value
public class GameDto {
    Long id;
    UserDto userBlack;
    UserDto userWhite;
    ChessFairDto chessFair;
}