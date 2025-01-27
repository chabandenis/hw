package ru.otus.hw.ex10.dto;

import lombok.Data;
import ru.otus.hw.ex10.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex10.models.ChessFair;

import java.util.List;

/**
 * DTO for {@link ChessFair}
 */
@Data
public class ChessFairDto {

    private Long id;

    // вид удобный для отображения в таблице в TL
    private List<RowOnTheDeskDto> desk;

}