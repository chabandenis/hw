package ru.otus.hw.ex17_front_game.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//
@Data
public class RowOnTheDeskDto {

    private String leftClm;

    private String rightClm;

    private Map<Integer, ClmDto> arr = new HashMap<>();
}
