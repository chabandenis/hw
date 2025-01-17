package ru.otus.hw.ex11.dto;

import lombok.Data;
import ru.otus.hw.ex11.models.Figura;

/**
 * DTO for {@link Figura}
 */
@Data
public class FiguraDto {

    private Long id;

    private String name;
}