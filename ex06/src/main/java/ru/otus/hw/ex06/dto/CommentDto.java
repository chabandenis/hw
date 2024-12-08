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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CommentDto that = (CommentDto) o;
        return id == that.id && bookId == that.bookId && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Objects.hashCode(text);
        result = 31 * result + Long.hashCode(bookId);
        return result;
    }
}