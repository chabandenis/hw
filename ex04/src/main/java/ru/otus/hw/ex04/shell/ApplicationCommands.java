package ru.otus.hw.ex04.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw.ex04.service.TestRunnerService;

@ShellComponent(value = "Application Events Commands")
@RequiredArgsConstructor
public class ApplicationCommands {

    private final TestRunnerService testRunnerService;

    @ShellMethod(value = "Start Application", key = {"s", "start"})
    public String start(@ShellOption(defaultValue = "AnyUser") String nothing) {
        testRunnerService.run();
        return "Приложение выполнено";
    }
}
