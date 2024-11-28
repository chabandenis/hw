package ru.otus.hw.ex06.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex06.converters.GenreConverter;
import ru.otus.hw.ex06.services.GenreService;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final GenreService genreService;

    private final GenreConverter genreConverter;

    @ShellMethod(value = "Find book by id", key = "bbid")
    public String findBookById(long id) {
/*        return bookService.findById(id)
                .map(bookConverter::bookToString)
                .orElse("Book with id %d not found".formatted(id));

 */
        return null;
    }
/*
    @ShellMethod(value = "Find all genres", key = "ag")
    public String findAllGenres() {
        return genreService.findAll().stream()
                .map(genreConverter::genreToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Find genres by ids", key = "ag2")
    public String findGenresByIds(String ids) {
        return
                genreService.findAllByIds(
                                Arrays.stream(ids.split(","))
                                        .map(x -> Long.valueOf(x.replace(" ", "")))
                                        .collect(Collectors.toSet())
                        ).stream()
                        .map(genreConverter::genreToString)
                        .collect(Collectors.joining("," + System.lineSeparator()));
    }
*/
}
