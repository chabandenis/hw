package ru.otus.hw.ex07.dto;

import lombok.Value;
import ru.otus.hw.ex07.models.Comment;

/**
 * DTO for {@link Comment}
 */
@Value
public class CommentDto {
    private long id;

    private String text;

    private long bookId;
}