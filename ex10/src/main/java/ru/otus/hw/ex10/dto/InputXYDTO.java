package ru.otus.hw.ex10.dto;

import lombok.Data;

//@Component
@Data
public class InputXYDTO {
    // идентификатор игры
    private Long gameId;

    private String x1;

    private String y1;

    private String x2;

    private String y2;

}
