package ru.otus.hw.ex06.dto;

import lombok.Value;
import ru.otus.hw.ex06.models.CommentBook;

/**
 * DTO for {@link CommentBook}
 */
@Value
public class CommentBookDto {
    private long id;

    private String text;

    private long bookId;
}