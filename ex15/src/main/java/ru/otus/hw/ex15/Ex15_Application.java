package ru.otus.hw.ex15;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@SpringBootApplication
@EnableWebMvc
public class Ex15_Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex15_Application.class, args);
        log.debug("ресты");
    }

}
