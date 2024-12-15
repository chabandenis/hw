package ru.otus.hw.ex06.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.services.BookService;
import ru.otus.hw.ex06.services.CommentService;

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
    @ShellMethod(value = "Find book by id", key = "cs")
    public String create(String text, long bookId) {
        return commentConverter.commentDtoToString(commentService.create(bookId, text));
    }

    // cbbi 1
    // updt 1 1111
    // cbbi 1
    @ShellMethod(value = "Find book by id", key = "updt")
    public String update(long commentId, String text) {
        return commentConverter.commentDtoToString(commentService.update(commentId, text));
    }

}
