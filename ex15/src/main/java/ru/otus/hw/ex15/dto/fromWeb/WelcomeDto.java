package ru.otus.hw.ex15.dto.fromWeb;

import lombok.Data;
import ru.otus.hw.ex15.dto.GameDto;

import java.util.List;

@Data
public class WelcomeDto {

    private String name;

    private List<GameDto> games;
}