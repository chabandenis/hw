package ru.otus.hw.ex16.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.repository.EggRepository;

@AllArgsConstructor
@Component
public class Run {

    private final EggRepository eggRepository;

    private void generateEgg() {
        for (int i = 0; i < 10; i++) {
            var egg = eggRepository.save(Egg.builder().name("00"+i).build());
            System.out.println("egg" + egg);
        }
    }

    public void run() throws Exception {
        System.out.println("start");
        generateEgg();
    }
}
