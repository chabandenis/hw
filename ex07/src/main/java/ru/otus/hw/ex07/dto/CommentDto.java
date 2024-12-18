package ru.otus.hw.ex07.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw.ex07.models.Comment;

/**
 * DTO for {@link Comment}
 */
@Data
@AllArgsConstructor
public class CommentDto {
    private long id;

    private String text;

    private long bookId;
}