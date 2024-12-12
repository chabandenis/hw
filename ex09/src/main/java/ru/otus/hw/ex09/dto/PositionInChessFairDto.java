package ru.otus.hw.ex09.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex09.models.PositionInChessFair}
 */
@Value
public class PositionInChessFairDto {
    Long id;
    Integer positionX;
    Integer positionY;
    ChessFairDto chessFair;
    FiguraDto figura;
}