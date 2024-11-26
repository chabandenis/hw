package ru.otus.hw.ex06.dto;

import lombok.Value;

import java.util.List;

/**
 * DTO for {@link ru.otus.hw.ex06.models.Book}
 */
@Value
public class BookDto {
    Long id;
    String title;
    Long authorId;
    String authorFullName;
    List<GenreDto> genres;
    List<CommentDto> comments;
}