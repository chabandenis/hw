package ru.otus.hw.ex07.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex07.converters.CommentConverter;
import ru.otus.hw.ex07.dto.CommentDto;
import ru.otus.hw.ex07.services.BookService;
import ru.otus.hw.ex07.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    private final BookService bookService;

    @ShellMethod(value = "Find book by id", key = "cbbi")
    public String findByBookId(long bookId) {
        return commentService.findCommentsByBookId(bookId).stream()
                .map(commentConverter::commentDtoToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Find book by id", key = "cfbi")
    public String findById(long id) {
        return commentConverter.commentDtoToString(commentService.findById(id).get());
    }


    // cdbi 1
    @ShellMethod(value = "Find book by id", key = "cdbi")
    public String deleteById(long id) {
        commentService.deleteById(id);
        return "удален";
    }

    // cdbbi 1
    // cbbi 1
    @ShellMethod(value = "Find book by id", key = "cdbbi")
    public String deleteByBookId(long bookId) {
        commentService.deleteByBookId(bookId);
        return "удален";
    }


    // cs 1111 1
    // cbbi 1
    @ShellMethod(value = "save comment", key = "cs")
    public String save(String text, long bookId) {
        CommentDto savedComment;
        var comment = commentService.findById(bookId);
        if (comment.isEmpty()) {
            savedComment = commentService.create(bookId, text);
        } else {
            savedComment = commentService.update(bookId, text);
        }
        ;

        return savedComment.getText();
    }
}
