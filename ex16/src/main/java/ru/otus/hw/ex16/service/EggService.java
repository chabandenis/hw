package ru.otus.hw.ex16.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Caterpillar;
import ru.otus.hw.ex16.model.Egg;

@Slf4j
@Component
public class EggService {

    // оплодотворение
    public static Caterpillar fertilization(Egg egg, Butterfly butterfly) {
        log.debug("оплодотворение");
        return Caterpillar.builder().name(egg + " + " + butterfly).build();
    }

}
