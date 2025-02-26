package ru.otus.hw.ex00.services.job;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex00.services.RepaymentService;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
@AllArgsConstructor
public class MakeDeclJob {

    private final RepaymentService repaymentService;

    public void start() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("MakeDecl job here !!! " + LocalDateTime.now()
                    + "; " + Thread.currentThread().getId());

        }, 200, 5000, TimeUnit.MILLISECONDS);

    }

}
