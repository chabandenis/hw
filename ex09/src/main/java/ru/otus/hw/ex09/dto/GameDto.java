package ru.otus.hw.ex09.dto;

import lombok.Data;

/**
 * DTO for {@link ru.otus.hw.ex09.models.Game}
 */
@Data
public class GameDto {
    private Long id;

    private UserDto userBlack;

    private UserDto userWhite;

    private UserDto userNext;

    private ChessFairDto chessFair;
}