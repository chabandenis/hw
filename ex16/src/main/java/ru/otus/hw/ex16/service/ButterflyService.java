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
        var eggs = List.of(
                new Egg(null, butterfly.getName() + "=> Яйцо 001"),
                new Egg(null, butterfly.getName() + "=> Яйцо 002"));
        log.debug("Откладывание яиц. " + eggs.toString());
        return eggs;
    }

}
