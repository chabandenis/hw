package ru.otus.hw.ex15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Ex15_Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex15_Application.class, args);
        System.out.println("ресты");
    }

}
