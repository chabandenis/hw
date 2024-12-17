package ru.otus.hw.ex09.dto.desk;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex09.dto.PositionInChessFairDto;

@Data
@AllArgsConstructor
public class ClmDto {

    private String val;

    private Long positionId;
}
