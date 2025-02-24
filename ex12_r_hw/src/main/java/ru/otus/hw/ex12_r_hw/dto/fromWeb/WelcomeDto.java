package ru.otus.hw.ex12_r_hw.dto.fromWeb;

import lombok.Data;
import ru.otus.hw.ex12_r_hw.dto.GameDto;

import java.util.List;

@Data
public class WelcomeDto {

    private String name;

    private List<GameDto> games;
}