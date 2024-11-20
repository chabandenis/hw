package ru.otus.hw.ex05.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex05.converters.BookGenreConverter;
import ru.otus.hw.ex05.services.BookGenreService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class BookGenreCommands {

    private final BookGenreService bookGenreService;

    private final BookGenreConverter bookGenreConverter;

    @ShellMethod(value = "Find genres by book id", key = "abg")
    public String findGenresByBookId(Long id) {
        return
                bookGenreService.findAllByBookId(id)
                        .stream()
                        .map(bookGenreConverter::bookGenreToString)
                        .collect(Collectors.joining("," + System.lineSeparator()));
    }

}
