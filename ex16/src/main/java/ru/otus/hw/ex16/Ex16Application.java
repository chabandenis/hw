package ru.otus.hw.ex16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw.ex16.service.Run;

@SpringBootApplication
public class Ex16Application {
    public static void main(String[] args) {

        var context = SpringApplication.run(Ex16Application.class, args);

        var testRunnerService = context.getBean(Run.class);

        try {
            testRunnerService.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
