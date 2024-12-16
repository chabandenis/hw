package ru.otus.hw.ex09.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class InputXYDTO {
    private List<String> xFrom = List.of("A", "B", "C", "D", "E", "F", "G", "H");
    private List<String> yFrom = List.of("1", "2", "3", "4", "5", "6", "7", "8");

    private List<String> xTo = List.of("A", "B", "C", "D", "E", "F", "G", "H");
    private List<String> yTo = List.of("1", "2", "3", "4", "5", "6", "7", "8");

    // Координаты на доске.
    // Я пробовал сделать числа, но они обрабатываются как строка и ломается спринг, вместо прикладного обработчика
    private String xFirst = "";
    private String yFirst;
    private String xSecond = "";
    private String ySecond;

}
