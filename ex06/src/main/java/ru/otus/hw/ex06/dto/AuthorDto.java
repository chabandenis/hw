package ru.otus.hw.ex06.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex06.models.Author}
 */
@Value
public class AuthorDto {
    Long id;
    String fullName;
}