package ru.otus.hw.ex15.dto;

import lombok.Data;
import ru.otus.hw.ex15.models.PositionInChessFair;

/**
 * DTO for {@link PositionInChessFair}
 */
@Data
public class PositionInChessFairInDbDto {

    private Long id;

    private Integer positionX;

    private Integer positionY;

    private Long chessFairId;

    private Long figuraId;

    private String figuraName;
}