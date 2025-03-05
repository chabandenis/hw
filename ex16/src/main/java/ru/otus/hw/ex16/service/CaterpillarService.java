package ru.otus.hw.ex16.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Caterpillar;
import ru.otus.hw.ex16.model.Grass;
import ru.otus.hw.ex16.model.Pupae;

@Slf4j
@Component
public class CaterpillarService {

    // Растет
    public Pupae growing(Caterpillar caterpillar, Grass grass) {
        log.debug("превращение в куколку");
        return new Pupae(null, caterpillar + " + " + grass);
    }
}
