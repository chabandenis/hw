package ru.otus.hw.ex13.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.ex13.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex13.models.ChessFair;

import java.util.List;

/**
 * DTO for {@link ChessFair}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChessFairDto {

    private Long id;

    // вид удобный для отображения в таблице в TL
    private List<RowOnTheDeskDto> desk;

}