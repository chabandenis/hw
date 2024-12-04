package ru.otus.hw.ex07.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex07.dto.CommentBookDto;
import ru.otus.hw.ex07.models.CommentBook;

@Component
public class CommentConverter {
    public CommentBookDto toDto(CommentBook comment) {
        CommentBookDto commentDto = new CommentBookDto(
                comment.getId(),
                comment.getText(),
                comment.getBook().getId());
        return commentDto;
    }

    public String commentDtoToString(CommentBookDto commentDto) {
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
