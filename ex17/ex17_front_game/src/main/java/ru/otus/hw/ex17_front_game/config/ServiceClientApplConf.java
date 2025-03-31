package ru.otus.hw.ex17_front_game.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.micrometer.MicrometerCapability;
import feign.micrometer.MicrometerObservationCapability;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.otus.hw.ex17_front_game.controller.*;
import ru.otus.hw.ex17_front_game.filter.MdcFilter;
import ru.otus.hw.ex17_front_game.metrics.MetricsManager;
import ru.otus.hw.ex17_front_game.metrics.MicrometerMetricsManager;

import java.time.Duration;

@Configuration
@Import(FeignClientsConfiguration.class)
public class ServiceClientApplConf {

    @Bean
    public RateLimiterConfig rateLimiterConfig() {
        return RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(100))
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(1000)
                .build();
    }

    @Bean
    public RateLimiter rateLimiter(RateLimiterConfig config) {
        return RateLimiter.of("defaultRateLimiter", config);
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(5))
                        .build())
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .build());
    }

    @Bean
    public CircuitBreaker circuitBreaker(CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        return circuitBreakerFactory.create("defaultCircuitBreaker");
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> mdcFilterRegistrationBean() {
        var registrationBean = new FilterRegistrationBean<OncePerRequestFilter>();
        registrationBean.setFilter(new MdcFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder().build();
    }

    @Bean
    public RequestCreate requestCreateMethod(
            Decoder decoder,
            Encoder encoder,
            Contract contract,
            ObjectMapper mapper,
            MeterRegistry meterRegistry,
            ObservationRegistry observationRegistry) {

        return Feign.builder()
                .encoder(new RequestEncoder(encoder))
                .decoder(new ResponseDecoder(decoder, mapper))
                .contract(contract)
                .logLevel(Logger.Level.FULL)
                .addCapability(new MicrometerObservationCapability(observationRegistry)) // <-- THIS IS NEW
                .addCapability(new MicrometerCapability(meterRegistry)) // <-- THIS IS NEW
                .retryer(new Retryer.Default(500, 5_000, 10))
                .target(RequestCreate.class, "http");
    }

    @Bean
    public RequestDelete requestDeleteMethod(
            Decoder decoder,
            Encoder encoder,
            Contract contract,
            ObjectMapper mapper,
            MeterRegistry meterRegistry,
            ObservationRegistry observationRegistry) {

        return Feign.builder()
                .encoder(new RequestEncoder(encoder))
                .decoder(new ResponseDecoder(decoder, mapper))
                .contract(contract)
                .logLevel(Logger.Level.FULL)
                .addCapability(new MicrometerObservationCapability(observationRegistry)) // <-- THIS IS NEW
                .addCapability(new MicrometerCapability(meterRegistry)) // <-- THIS IS NEW
                .retryer(new Retryer.Default(500, 5_000, 10))
                .target(RequestDelete.class, "http");
    }

    @Bean
    public RequestGamesForUsers requestGamesForUsersMethod(
            Decoder decoder,
            Encoder encoder,
            Contract contract,
            ObjectMapper mapper,
            MeterRegistry meterRegistry,
            ObservationRegistry observationRegistry) {

        return Feign.builder()
                .encoder(new RequestEncoder(encoder))
                .decoder(new ResponseDecoderGamesForUsers(decoder, mapper))
                .contract(contract)
                .logLevel(Logger.Level.FULL)
                .addCapability(new MicrometerObservationCapability(observationRegistry)) // <-- THIS IS NEW
                .addCapability(new MicrometerCapability(meterRegistry)) // <-- THIS IS NEW
                .retryer(new Retryer.Default(500, 5_000, 10))
                .target(RequestGamesForUsers.class, "http");
    }

    @Bean
    public RequestGetOne requestGetOneMethod(
            Decoder decoder,
            Encoder encoder,
            Contract contract,
            ObjectMapper mapper,
            MeterRegistry meterRegistry,
            ObservationRegistry observationRegistry) {

        return Feign.builder()
                .encoder(new RequestEncoder(encoder))
                .decoder(new ResponseDecoderGetOne(decoder, mapper))
                .contract(contract)
                .logLevel(Logger.Level.FULL)
                .addCapability(new MicrometerObservationCapability(observationRegistry)) // <-- THIS IS NEW
                .addCapability(new MicrometerCapability(meterRegistry)) // <-- THIS IS NEW
                .retryer(new Retryer.Default(500, 5_000, 10))
                .target(RequestGetOne.class, "http");
    }

    @Bean
    public RequestStep RequestStepMethod(
            Decoder decoder,
            Encoder encoder,
            Contract contract,
            ObjectMapper mapper,
            MeterRegistry meterRegistry,
            ObservationRegistry observationRegistry) {

        return Feign.builder()
                .encoder(new RequestEncoder(encoder))
                .decoder(new ResponseDecoder(decoder, mapper))
                .contract(contract)
                .logLevel(Logger.Level.FULL)
                .addCapability(new MicrometerObservationCapability(observationRegistry)) // <-- THIS IS NEW
                .addCapability(new MicrometerCapability(meterRegistry)) // <-- THIS IS NEW
                .retryer(new Retryer.Default(500, 5_000, 10))
                .target(RequestStep.class, "http");
    }

    @Bean
    public MetricsManager micrometerMetricsManager(MeterRegistry meterRegistry) {
        return new MicrometerMetricsManager(meterRegistry);
    }
}