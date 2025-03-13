package ru.otus.hw.ex17.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UsersInDbHealthIndicator implements HealthIndicator {

    private final Random random = new Random();

    @Override
    public Health health() {
        boolean serverIsDown = random.nextBoolean();
        if (serverIsDown) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Караул!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Ура, товарищи!").build();
        }
    }
}
