package ru.otus.hw.ex16.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import ru.otus.hw.ex16.model.GeneratedId;

import java.util.UUID;

@Configuration
public class PersistenceConfig {

    @Bean
    public ApplicationListener<BeforeSaveEvent> idGenerator() {
        return event -> {
            var entity = event.getEntity();
            if (entity instanceof GeneratedId) {
                ((GeneratedId) entity).setId(UUID.randomUUID().toString());
            }
        };
    }
}
