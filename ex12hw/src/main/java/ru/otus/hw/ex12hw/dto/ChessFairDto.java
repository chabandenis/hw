package ru.otus.hw.ex12hw.dto;

import lombok.Data;
import ru.otus.hw.ex12hw.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex12hw.models.ChessFair;

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