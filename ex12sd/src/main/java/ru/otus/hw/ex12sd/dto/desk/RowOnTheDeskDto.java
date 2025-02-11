package ru.otus.hw.ex12sd.dto.desk;

import lombok.Data;
import ru.otus.hw.ex12sd.dto.desk.ClmDto;

import java.util.HashMap;
import java.util.Map;

//
@Data
public class RowOnTheDeskDto {

    private String leftClm;

    private String rightClm;

    private Map<Integer, ClmDto> arr = new HashMap<>();
}
