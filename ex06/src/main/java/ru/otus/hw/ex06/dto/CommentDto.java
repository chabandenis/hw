package ru.otus.hw.ex06.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex06.models.Comment}
 */
@Value
public class CommentDto {
    Long id;
    String text;
}