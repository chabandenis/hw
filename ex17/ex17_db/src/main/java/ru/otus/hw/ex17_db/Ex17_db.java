package ru.otus.hw.ex17_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex17_db {

    public static void main(String[] args) {
        SpringApplication.run(Ex17_db.class, args);
        System.out.println("миграция");
    }
}
