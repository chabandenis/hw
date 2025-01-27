package ru.otus.hw.ex10.dto;

import lombok.Data;
import ru.otus.hw.ex10.models.Figura;

/**
 * DTO for {@link Figura}
 */
@Data
public class FiguraDto {

    private Long id;

    private String name;
}