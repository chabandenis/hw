package ru.otus.hw.ex17_front_game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

//
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowOnTheDeskDto {

    private String leftClm;

    private String rightClm;

    private Map<Integer, ClmDto> arr = new HashMap<>();
}
