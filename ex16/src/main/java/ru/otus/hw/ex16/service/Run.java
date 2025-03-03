package ru.otus.hw.ex16.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.model.Grass;
import ru.otus.hw.ex16.model.Sun;
import ru.otus.hw.ex16.model.Сaterpillar;
import ru.otus.hw.ex16.repository.ButterflyRepository;
import ru.otus.hw.ex16.repository.EggRepository;
import ru.otus.hw.ex16.repository.GrassRepository;
import ru.otus.hw.ex16.repository.SunRepository;
import ru.otus.hw.ex16.repository.СaterpillarRepository;

@AllArgsConstructor
@Component
public class Run {

    private final ButterflyRepository butterflyRepository;
    private final EggRepository eggRepository;
    private final GrassRepository grassRepository;
    private final SunRepository sunRepository;
    private final СaterpillarRepository caterpillarRepository;

    private void generateButterfly() {
        for (int i = 0; i < 10; i++) {
            var butterfly = butterflyRepository.save(Butterfly.builder().name("00" + i).build());
            System.out.println("butterfly " + butterfly);
        }
    }

    private void generateEgg() {
        for (int i = 0; i < 10; i++) {
            var egg = eggRepository.save(Egg.builder().name("00" + i).build());
            System.out.println("egg" + egg);
        }
    }

    private void generateGrass() {
        for (int i = 0; i < 10; i++) {
            var grass = grassRepository.save(Grass.builder().name("00" + i).build());
            System.out.println("Grass" + grass);
        }
    }

    private void generateSun() {
        for (int i = 0; i < 10; i++) {
            var sun = sunRepository.save(Sun.builder().name("00" + i).build());
            System.out.println("Sun " + sun);
        }
    }

    private void generateСaterpillar() {
        for (int i = 0; i < 10; i++) {
            var caterpillar = caterpillarRepository.save(Сaterpillar.builder().name("00" + i).build());
            System.out.println("caterpillar " + caterpillar);
        }
    }


    public void run() throws Exception {
        System.out.println("start");
        generateButterfly();
        generateEgg();
        generateGrass();
        generateSun();
        generateСaterpillar();
    }
}
