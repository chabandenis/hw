package ru.otus.hw.ex06.dto;

import lombok.Value;
import ru.otus.hw.ex06.models.Author;

/**
 * DTO for {@link Author}
 */
@Value
public class AuthorDto {
    private long id;

    private String fullName;

}