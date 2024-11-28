package ru.otus.hw.ex06.dto;

import lombok.Value;
import ru.otus.hw.ex06.models.Comment;

/**
 * DTO for {@link Comment}
 */
@Value
public class CommentDto {
    long id;
    String text;
    long bookId;
}