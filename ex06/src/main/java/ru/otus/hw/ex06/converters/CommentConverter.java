package ru.otus.hw.ex06.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex06.models.Comment;

@Component
public class CommentConverter {
    public Comment toDto(Comment comment) {
        Comment commentDto = new Comment(
                comment.getId(),
                comment.getText(),
                comment.getBook().getId());
        return commentDto;
    }

    public String commentDtoToString(Comment commentDto) {
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
