package ru.otus.hw.ex12hw.dto;

import lombok.Data;
import ru.otus.hw.ex12hw.models.Figura;

/**
 * DTO for {@link Figura}
 */
@Data
public class FiguraDto {

    private Long id;

    private String name;
}