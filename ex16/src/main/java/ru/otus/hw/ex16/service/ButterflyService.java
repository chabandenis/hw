package ru.otus.hw.ex16.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.model.Sun;

import java.util.List;

@Slf4j
@Component
public class ButterflyService {
    // Растет
    public static List<Egg> growing(Butterfly butterfly, Sun sun) {
        log.debug("Откладывание яиц");
        return List.of(new Egg(null, "egg02"), new Egg(null, "egg03"));
    }

}
