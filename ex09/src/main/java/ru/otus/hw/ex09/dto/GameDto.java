package ru.otus.hw.ex09.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex09.models.Game}
 */
@Value
public class GameDto {
    Long id;
    UserDto userBlack;
    UserDto userWhite;
    UserDto userNext;
    ChessFairDto chessFair;
}