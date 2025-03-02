package ru.otus.hw.ex07.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.ex07.converters.AuthorConverter;
import ru.otus.hw.ex07.services.AuthorService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "Find all authors", key = "aa")
    public String findAllAuthors() {
        return authorService.findAll().stream()
                .map(authorConverter::authorDtoToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    // aabi 1
    @ShellMethod(value = "Find by id", key = "aabi")
    public String findAuthorById(long id) {
        return authorConverter.authorDtoToString(authorService.findById(id).get());
    }
}
