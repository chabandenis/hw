package ru.otus.hw.ex12sd.dto;

import lombok.Data;
import ru.otus.hw.ex12sd.dto.desk.RowOnTheDeskDto;


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