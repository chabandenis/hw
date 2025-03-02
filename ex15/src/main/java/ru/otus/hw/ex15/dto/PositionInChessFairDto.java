package ru.otus.hw.ex15.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.ex15.models.PositionInChessFair;

/**
 * DTO for {@link PositionInChessFair}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionInChessFairDto {

    private Long id;

    private Integer positionX;

    private Integer positionY;

    private ChessFairDto chessFair; // доска

    private FiguraDto figura;   // шашка белая/черная
}