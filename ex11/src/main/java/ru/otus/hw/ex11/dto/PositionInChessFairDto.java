package ru.otus.hw.ex11.dto;

import lombok.Data;
import ru.otus.hw.ex11.models.PositionInChessFair;

/**
 * DTO for {@link PositionInChessFair}
 */
@Data
public class PositionInChessFairDto {

    private Long id;

    private Integer positionX;

    private Integer positionY;

    private ChessFairDto chessFair; // доска

    private FiguraDto figura;   // шашка белая/черная
}