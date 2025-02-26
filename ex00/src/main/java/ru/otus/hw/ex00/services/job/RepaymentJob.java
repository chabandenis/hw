package ru.otus.hw.ex00.services.job;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex00.services.RepaymentService;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class RepaymentJob {

    private final RepaymentService repaymentService;

    public void start(){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("Repayment job here !!! " + LocalDateTime.now());

            System.out.println( "Найдена запись " + repaymentService.findFirstByStatus().orElse(null));

        }, 100, 5000, TimeUnit.MILLISECONDS);

    }

}
