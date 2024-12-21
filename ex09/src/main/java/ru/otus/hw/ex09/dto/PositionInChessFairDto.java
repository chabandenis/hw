package ru.otus.hw.ex09.dto;

import lombok.Data;

/**
 * DTO for {@link ru.otus.hw.ex09.models.PositionInChessFair}
 */
@Data
public class PositionInChessFairDto {

    private Long id;

    private Integer positionX;

    private Integer positionY;

    private ChessFairDto chessFair;

    private FiguraDto figura;
}