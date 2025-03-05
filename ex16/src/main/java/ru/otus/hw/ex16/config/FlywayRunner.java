package ru.otus.hw.ex16.config;

import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class FlywayRunner {

    private final Flyway flyway;


    @Bean
    public CommandLineRunner cleanAndMigrate() {
        return args -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}
