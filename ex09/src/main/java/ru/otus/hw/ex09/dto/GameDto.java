package ru.otus.hw.ex09.dto;

import lombok.Data;
import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex09.models.Game}
 */
@Data
public class GameDto {
    Long id;
    UserDto userBlack;
    UserDto userWhite;
    UserDto userNext;
    ChessFairDto chessFair;
}