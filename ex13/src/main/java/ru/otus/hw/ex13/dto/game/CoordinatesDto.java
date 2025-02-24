package ru.otus.hw.ex13.dto.game;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

//@Component
@Data
public class CoordinatesDto {

    @NotNull
    private String x1;

    @NotNull
    private String y1;

    @NotNull
    private String x2;

    @NotNull
    private String y2;

}
