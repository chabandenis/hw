package ru.otus.hw.ex16.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Caterpillar;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.model.Grass;
import ru.otus.hw.ex16.model.Pupae;
import ru.otus.hw.ex16.model.Sun;
import ru.otus.hw.ex16.service.ButterflyService;
import ru.otus.hw.ex16.service.CaterpillarService;
import ru.otus.hw.ex16.service.EggGateway;
import ru.otus.hw.ex16.service.EggService;
import ru.otus.hw.ex16.service.PupaeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@SpringBootApplication
@IntegrationComponentScan
@Slf4j
@Import(EggGateway.class)
public class GatewayApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(GatewayApp.class, args);
        Map<String, MessageChannel> channels = ctx.getBeansOfType(MessageChannel.class);
        log.warn("CHANNELS:");
        int i = 0;
        for (Map.Entry<String, MessageChannel> entry : channels.entrySet()) {
            log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
        }
        log.warn("HANDLERS:");
        i = 0;
        Map<String, MessageHandler> endpoints = ctx.getBeansOfType(MessageHandler.class);
        for (Map.Entry<String, MessageHandler> entry : endpoints.entrySet()) {
            log.warn("{}. {}/{} -> {}", ++i, entry.getKey(), entry.getValue().getClass().getSimpleName(), entry.getValue());
        }
        Butterflys trans = ctx.getBean(Butterflys.class);
        Collection<ru.otus.hw.ex16.model.Butterfly> result =
                trans.life(
                        List.of(
                                new Egg(null, "Яйцо 001"),
                                new Egg(null, "Яйцо 002"))
                );
        log.warn("Upcase result: {}", result);

    }

    @MessagingGateway
    public interface Butterflys {
        @Gateway(requestChannel = "upcase33.input")
        Collection<Butterfly> life(Collection<Egg> strings);
    }

    @Bean
    public IntegrationFlow upcase33() {
        return f -> f//.channel("from-input-to-split")
                .split()
                .channel("from-split-to-transformer")

                // 1. Яйца (гусеницы) + бабочка мальчик => Гусеница
                .<Egg, Caterpillar>transform(
                        egg -> EggService.fertilization(
                                egg,
                                new ru.otus.hw.ex16.model.Butterfly(null, "Бабочка 001")))

                .channel("from-Caterpillar-to-Pupae")

                //  2. гусеницы + трава => куколки
                //var pupae = caterpillarService.growing(caterpillar, grass);
                .<Caterpillar, Pupae>transform(
                        caterpillar -> CaterpillarService.growing(
                                caterpillar, new Grass(null, "Greass001")))
                .channel("from-Pupae-to-Butterfly")

                // 3. из куколки в бабочку
                .<Pupae, Butterfly>transform(PupaeService::growing)
                .channel("from-Butterfly-to-Eggs")

                // 4. бабочка откладывает яйца
                .<Butterfly, List<Egg>>transform(
                        pupae -> ButterflyService.growing(
                                pupae,
                                new Sun(null, "sun 001")))

                .channel("from-transformer-to-aggregate")
                .aggregate();
    }
}
