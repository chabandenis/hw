package ru.otus.hw.ex07.dto;

import lombok.Value;
import ru.otus.hw.ex07.models.Genre;

/**
 * DTO for {@link Genre}
 */
@Value
public class GenreDto {
    private long id;

    private String name;
}