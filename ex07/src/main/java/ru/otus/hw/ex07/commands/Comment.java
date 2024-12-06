package ru.otus.hw.ex07.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex07.converters.CommentConverter;
import ru.otus.hw.ex07.models.Book;
import ru.otus.hw.ex07.services.BookService;
import ru.otus.hw.ex07.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class Comment {

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
    public String save(String text, long bookId) {
        ru.otus.hw.ex07.models.Comment comment = new ru.otus.hw.ex07.models.Comment();
        comment.setText(text);

        Book book = new Book();
        book.setId(bookId);

        comment.setBook(book);

        commentService.save(comment);
        return commentConverter.commentDtoToString(commentService.save(comment));
    }
}
