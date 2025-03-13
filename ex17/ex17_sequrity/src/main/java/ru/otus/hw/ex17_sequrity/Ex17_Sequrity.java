package ru.otus.hw.ex17_sequrity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex17_Sequrity {

    public static void main(String[] args) {
        SpringApplication.run(Ex17_Sequrity.class, args);
        System.out.println("jwt");
    }
}
