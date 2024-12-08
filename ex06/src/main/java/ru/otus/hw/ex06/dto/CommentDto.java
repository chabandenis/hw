package ru.otus.hw.ex06.dto;

import lombok.Value;
import ru.otus.hw.ex06.models.Comment;

import java.util.Objects;

/**
 * DTO for {@link Comment}
 */
@Value
public class CommentDto {
    private long id;

    private String text;

    private long bookId;

}