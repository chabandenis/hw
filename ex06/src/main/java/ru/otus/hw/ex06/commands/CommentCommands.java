package ru.otus.hw.ex06.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex06.converters.CommentConverter;
import ru.otus.hw.ex06.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

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

}
