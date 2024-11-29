package ru.otus.hw.ex06.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex06.models.Genre}
 */
@Value
public class GenreDto {
    long id;
    String name;
}