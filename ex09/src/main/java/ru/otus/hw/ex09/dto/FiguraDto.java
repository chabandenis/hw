package ru.otus.hw.ex09.dto;

import lombok.Data;
import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex09.models.Figura}
 */
@Data
public class FiguraDto {
    Long id;
    String name;
}