package ru.otus.hw.ex16.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.repository.EggRepository;

@AllArgsConstructor
@Component
public class Run implements ApplicationRunner {

    private final EggRepository eggRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("start");

        Egg egg = Egg.builder()/*.id("5")*/.name("001").build();
        eggRepository.save(egg);

        System.out.println("egg " + egg);


    }
}
