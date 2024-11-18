package ru.otus.hw.ex04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.hw.ex04.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application_ex03 {
    public static void main(String[] args) {
       SpringApplication.run(Application_ex03.class, args);
    }
}