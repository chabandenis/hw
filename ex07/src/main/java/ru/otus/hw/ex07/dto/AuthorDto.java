package ru.otus.hw.ex07.dto;

import lombok.Value;
import ru.otus.hw.ex07.models.Author;

/**
 * DTO for {@link Author}
 */
@Value
public class AuthorDto {
    private long id;

    private String fullName;
}