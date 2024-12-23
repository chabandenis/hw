package ru.otus.hw.ex10.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InputXYDTO {

/*
    private List<String> xFrom = List.of("A", "B", "C", "D", "E", "F", "G", "H");
    private List<String> yFrom = List.of("1", "2", "3", "4", "5", "6", "7", "8");

    private List<String> xTo = List.of("A", "B", "C", "D", "E", "F", "G", "H");
    private List<String> yTo = List.of("1", "2", "3", "4", "5", "6", "7", "8");
*/

    private String xFirst;

    private String yFirst;

    private String xSecond;

    private String ySecond;

}
