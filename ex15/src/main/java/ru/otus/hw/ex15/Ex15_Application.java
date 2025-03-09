package ru.otus.hw.ex15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication
public class Ex15_Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex15_Application.class, args);
        System.out.println("Только ресты");
    }
}
