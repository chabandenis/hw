package ru.otus.hw.ex16.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.model.Pupae;
import ru.otus.hw.ex16.model.Sun;
import ru.otus.hw.ex16.service.ButterflyService;
import ru.otus.hw.ex16.service.PupaeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@SpringBootApplication
@IntegrationComponentScan
@Slf4j
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
        Upcase2 upcase = ctx.getBean(Upcase2.class);
        Collection<Butterfly> result =
                upcase.upcase2(
                        List.of(
                                new Pupae(null, "Куколка 1"),
                                new Pupae(null, "Куколка 2"))
                );
        log.warn("Upcase result: {}", result);

    }

    @MessagingGateway
    public interface Upcase2 {
        @Gateway(requestChannel = "upcase33.input")
        Collection<Butterfly> upcase2(Collection<Pupae> strings);
    }

    @Bean
    public IntegrationFlow upcase33() {
        return f -> f//.channel("from-input-to-split")
                .split()
//				.split(list -> list.getObject().spliterator())
//				.split(getCustomSplitter(), "split")
                .channel("from-split-to-transformer")

                .<Pupae, Butterfly>transform(PupaeService::growing) // 3. из куколки в бабочку

                .<Butterfly, List<Egg>>transform(ButterflyService::growing) // 4. бабочка откладывает яйца

                .channel("from-transformer-to-aggregate")
                .aggregate()
                ;
//				.<Collection<String>>filter(source -> source.stream().anyMatch(s -> s.startsWith("a")))
    }


//	@Bean
//	CustomSplitter getCustomSplitter() {
//		return new CustomSplitter();
//	}
//
//	public static class CustomSplitter {
//		public Collection<String> split(Message<Collection<String>> message) {
//			return message.getPayload().stream().skip(1).collect(Collectors.toList());
//		}
//	}


}
