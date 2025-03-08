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
import ru.otus.hw.ex16.model.Caterpillar;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.service.EggService;

@Slf4j
@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> eggsChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> caterpillarChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow lifeFlow() {
        log.debug("egg => caterpillar");
        return IntegrationFlow
                .from(eggsChannel())

                .<Egg, Caterpillar>transform(
                        egg -> EggService.fertilization(
                                egg,
                                new ru.otus.hw.ex16.model.Butterfly(null, "Бабочка 001")))

                .channel(caterpillarChannel())
                .get();
    }
}
