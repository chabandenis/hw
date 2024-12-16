package ru.otus.hw.ex09.dto;

import lombok.Data;
import ru.otus.hw.ex09.dto.desk.RowOnTheDeskDto;

import java.util.List;

/**
 * DTO for {@link ru.otus.hw.ex09.models.ChessFair}
 */
@Data
public class ChessFairDto {
    Long id;

    // вид удобный для отображения в таблице в TL
    List<RowOnTheDeskDto> desk;

    List<PositionInChessFairDto> positionInChessFairDtos;
}