package ru.otus.hw.ex09.dto;

import lombok.Data;
import lombok.Value;
import ru.otus.hw.ex09.models.Game;

/**
 * DTO for {@link Game}
 */
@Data
public class GameDto {
    Long id;
    UserDto userBlack;
    UserDto userWhite;
    ChessFairDto chessFair;
}