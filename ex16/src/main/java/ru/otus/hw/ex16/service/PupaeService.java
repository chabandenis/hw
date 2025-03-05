package ru.otus.hw.ex16.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Pupae;

@Slf4j
@Component
public class PupaeService {

    // Растет
    public Butterfly growing(Pupae pupae) {
        log.debug("превращение в бабочку");
        return new Butterfly(null, pupae.getName());
    }

}
