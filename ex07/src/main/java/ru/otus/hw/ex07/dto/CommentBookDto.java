package ru.otus.hw.ex07.dto;

import lombok.Value;
import ru.otus.hw.ex07.models.CommentBook;

/**
 * DTO for {@link CommentBook}
 */
@Value
public class CommentBookDto {
    private long id;

    private String text;

    private long bookId;
}