package ru.otus.hw.ex09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex09Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex09Application.class, args);
        System.out.println("Запуск игры http://localhost:8080");
    }


}
