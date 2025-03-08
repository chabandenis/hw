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
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Caterpillar;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.model.Grass;
import ru.otus.hw.ex16.model.Pupae;
import ru.otus.hw.ex16.model.Sun;
import ru.otus.hw.ex16.service.ButterflyService;
import ru.otus.hw.ex16.service.CaterpillarService;
import ru.otus.hw.ex16.service.EggService;
import ru.otus.hw.ex16.service.PupaeService;

import java.util.List;

@Slf4j
@Configuration
public class IntegrationConfig {

    @Bean
    // яйцо
    public MessageChannelSpec<?, ?> eggChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    // гусиница
    public MessageChannelSpec<?, ?> caterpillarChannel() {
        return MessageChannels.queue();
    }

    @Bean
    // куколка
    public MessageChannelSpec<?, ?> pupaeChannel() {
        return MessageChannels.queue();
    }

    @Bean
    //бабочка
    public MessageChannelSpec<?, ?> butterflyChannel() {
        return MessageChannels.queue();
    }

/*    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }*/

    @Bean
    public IntegrationFlow eggFlow() {
        log.debug("egg => caterpillar");
        return IntegrationFlow
                .from(eggChannel())
                .split()
                .<Egg, Caterpillar>transform(
                        egg -> EggService.fertilization(
                                egg,
                                new ru.otus.hw.ex16.model.Butterfly(null, "Бабочка 001")))
                .aggregate()
                .channel(caterpillarChannel())
                .get();
    }

    @Bean
    public IntegrationFlow сaterpillarFlow() {
        log.debug("сaterpillar => pupae");
        return IntegrationFlow
                .from(caterpillarChannel())
                .split()
                .<Caterpillar, Pupae>transform(
                        caterpillar -> CaterpillarService.growing(
                                caterpillar, new Grass(null, "Greass001")))
                .aggregate()
                .channel(pupaeChannel())
                .get();
    }

    @Bean
    public IntegrationFlow pupaeFlow() {
        log.debug("pupae => butterfly");
        return IntegrationFlow
                .from(pupaeChannel())
                .split()
                .<Pupae, Butterfly>transform(PupaeService::growing)
                .aggregate()
                .channel(butterflyChannel())
                .get();
    }

    @Bean
    public IntegrationFlow makeEggsFlow() {
        log.debug("butterfly => eggs");
        return IntegrationFlow
                .from(butterflyChannel())
                .split()
                .<Butterfly, List<Egg>>transform(
                        pupae -> ButterflyService.growing(
                                pupae,
                                new Sun(null, "sun 001")))
                .split()
                .channel(eggChannel())
                .get();
    }

}
