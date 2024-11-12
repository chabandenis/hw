package ru.otus.hw.ex03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.hw.ex03.config.AppProperties;
import ru.otus.hw.ex03.service.TestRunnerService;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application_ex03 {
    public static void main(String[] args) {

        //Создать контекст Spring Boot приложения
        ApplicationContext context = SpringApplication.run(Application_ex03.class, args);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}