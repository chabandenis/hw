package ru.otus.hw.ex00.services.job;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class RepaymentJob {

    public void start(){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("Repayment job here !!! " + LocalDateTime.now());

        }, 100, 5000, TimeUnit.MILLISECONDS);

    }

}
