package ru.otus.hw.ex09.web;

import lombok.Data;
import ru.otus.hw.ex09.dto.GameDto;

import java.util.List;

@Data
public class WelcomeDto {

    private String name;

    private List<GameDto> games;
}