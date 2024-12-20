package ru.otus.hw.ex05.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex05.converters.GenreConverter;
import ru.otus.hw.ex05.services.GenreService;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommands {

    private final GenreService genreService;

    private final GenreConverter genreConverter;

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

}
