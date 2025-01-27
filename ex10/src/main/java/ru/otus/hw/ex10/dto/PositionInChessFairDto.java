package ru.otus.hw.ex10.dto;

import lombok.Data;
import ru.otus.hw.ex10.models.PositionInChessFair;

/**
 * DTO for {@link PositionInChessFair}
 */
@Data
public class PositionInChessFairDto {

    private Long id;

    private Integer positionX;

    private Integer positionY;

    private ChessFairDto chessFair;

    private FiguraDto figura;
}