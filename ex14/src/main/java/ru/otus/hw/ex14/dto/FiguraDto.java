package ru.otus.hw.ex14.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.ex14.models.Figura;

/**
 * DTO for {@link Figura}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiguraDto {

    private Long id;

    private String name;
}