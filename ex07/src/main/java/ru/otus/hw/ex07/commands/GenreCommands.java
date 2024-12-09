package ru.otus.hw.ex07.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex07.converters.GenreConverter;
import ru.otus.hw.ex07.services.GenreService;

import java.util.Set;
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

    // ag2 "1, 3, 5"
    @ShellMethod(value = "Find genres by ids", key = "ag2")
    public String findGenresByIds(Set<Long> ids) {
        return
                genreService.findAllByIds(ids)
                        .stream()
                        .map(genreConverter::genreToString)
                        .collect(Collectors.joining("," + System.lineSeparator()));
    }

}
