package ru.otus.hw.ex16.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

@Slf4j
@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> itemsChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> foodChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow lifeFlow(/*KitchenService kitchenService*/) {
        log.debug("lifeFlow");
        return IntegrationFlow.from(itemsChannel())
//				.split()
//				.handle(kitchenService, "cook")
//				.<Food, Food>transform(f -> new Food(f.getName().toUpperCase()))
//				.aggregate()
                .channel(foodChannel())
                .get();
    }
}
