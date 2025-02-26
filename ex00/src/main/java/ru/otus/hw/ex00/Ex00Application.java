package ru.otus.hw.ex00;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class Ex00Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex00Application.class, args);
        System.out.println("ресты");
    }

}
