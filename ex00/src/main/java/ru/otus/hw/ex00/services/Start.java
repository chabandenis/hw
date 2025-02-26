package ru.otus.hw.ex00.services;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex00.services.job.RepaymentJob;

@Component
@AllArgsConstructor
public class Start implements ApplicationRunner {

    private final RepaymentJob repaymentJob;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repaymentJob.start();
    }
}
