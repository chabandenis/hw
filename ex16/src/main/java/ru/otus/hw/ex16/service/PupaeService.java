package ru.otus.hw.ex16.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Butterfly;
import ru.otus.hw.ex16.model.Pupae;

@Slf4j
@Component
public class PupaeService {

    // Растет
    public static Butterfly growing(Pupae pupae) {
        var butterfly = new Butterfly(null, pupae.getName() + "; превращение из куколки в бабочку ");
        log.debug("превращение в бабочку. " + butterfly.getName());
        return butterfly;
    }

}
