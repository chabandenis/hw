package ru.otus.hw.ex07.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex07.models.Author;

/**
 * DTO for {@link Author}
 */
@Data
@AllArgsConstructor
public class AuthorDto {
    private long id;

    private String fullName;
}