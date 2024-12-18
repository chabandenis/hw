package ru.otus.hw.ex07.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex07.models.Genre;

/**
 * DTO for {@link Genre}
 */
@Data
@AllArgsConstructor
public class GenreDto {
    private long id;

    private String name;
}