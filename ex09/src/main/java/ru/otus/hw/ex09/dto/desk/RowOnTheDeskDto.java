package ru.otus.hw.ex09.dto.desk;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//
@Data
public class RowOnTheDeskDto {
    private String leftClm;
    private String rightClm;

    private Map<Integer, ClmDto> arr = new HashMap<>();
    /*=
            Map.of(
                    0, new ClmDto("", null),
                    1, new ClmDto("", null),
                    2, new ClmDto("", null),
                    3, new ClmDto("", null),
                    4, new ClmDto("", null),
                    5, new ClmDto("", null),
                    6, new ClmDto("", null),
                    7, new ClmDto("", null)
            );*/
}
