package ru.otus.hw.ex12_r_hw.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.otus.hw.ex12_r_hw.models.Figura;

import java.util.Collection;

public interface FiguraRepository extends ReactiveCrudRepository<Figura, Long> {
    Flux<Figura> findByIdIn(Collection<Long> ids);

}