package ru.otus.ex02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.ex02.service.TestRunnerService;


@Configuration
@ComponentScan
public class Application_ex02 {
    public static void main(String[] args) {

        //Создать контекст на основе Annotation/Java конфигурирования
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Application_ex02.class);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}