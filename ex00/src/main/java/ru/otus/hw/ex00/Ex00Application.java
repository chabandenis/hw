package ru.otus.hw.ex00;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw.ex00.services.job.RepaymentJob;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@AllArgsConstructor
public class Ex00Application {


    public static void main(String[] args) {
        SpringApplication.run(Ex00Application.class, args);
        System.out.println("ресты");



    }


}
