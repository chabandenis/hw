package ru.otus.hw.ex06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Value;
import ru.otus.hw.ex06.models.Comment;

/**
 * DTO for {@link Comment}
 */
@Data
@AllArgsConstructor
public class CommentDto {
    private long id;

    private String text;

    private long bookId;

/*    @Override
    public boolean equals(Object o) {
        System.out.println("CommentDto");
        if (o == null || getClass() != o.getClass()) return false;

        CommentDto that = (CommentDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        System.out.println("CommentDto hash");
        return Long.hashCode(id);
    }*/


}