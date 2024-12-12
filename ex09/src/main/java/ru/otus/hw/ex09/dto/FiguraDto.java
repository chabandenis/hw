package ru.otus.hw.ex09.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex09.models.Figura}
 */
@Value
public class FiguraDto {
    Long id;
    String name;
}