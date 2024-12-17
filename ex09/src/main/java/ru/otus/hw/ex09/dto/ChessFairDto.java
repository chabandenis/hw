package ru.otus.hw.ex09.dto;

import lombok.Data;
import ru.otus.hw.ex09.dto.desk.RowOnTheDeskDto;

import java.util.List;

/**
 * DTO for {@link ru.otus.hw.ex09.models.ChessFair}
 */
@Data
public class ChessFairDto {

    private Long id;

    // вид удобный для отображения в таблице в TL
    private List<RowOnTheDeskDto> desk;

    private List<PositionInChessFairDto> positionInChessFairDtos;
}