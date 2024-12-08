package ru.otus.hw.ex06.dto;

import lombok.Value;
import ru.otus.hw.ex06.models.Comment;

/**
 * DTO for {@link Comment}
 */
@Value
public class CommentDto {
    private long id;

    private String text;

    private long bookId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CommentDto that = (CommentDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}