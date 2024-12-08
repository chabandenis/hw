package ru.otus.hw.ex06.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex06.dto.CommentDto;
import ru.otus.hw.ex06.models.Comment;

@Component
public class CommentConverter {
    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getBook().getId());
        return commentDto;
    }

    public String commentDtoToString(CommentDto commentDto) {
        if (commentDto == null) {
            return "";
        }
        return "Id: %d, BookId: %d, Text: %s"
                .formatted(
                        commentDto.getId(),
                        commentDto.getBookId(),
                        commentDto.getText()
                );
    }
}
