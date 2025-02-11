package ru.otus.hw.ex12sd.dto;

import lombok.Data;
import ru.otus.hw.ex12sd.models.Figura;

/**
 * DTO for {@link Figura}
 */
@Data
public class FiguraDto {

    private Long id;

    private String name;
}