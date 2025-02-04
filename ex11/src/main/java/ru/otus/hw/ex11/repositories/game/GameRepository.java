package ru.otus.hw.ex11.repositories.game;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex11.models.Game;

public interface GameRepository extends ReactiveCrudRepository<Game, Long> {


    @Override
    Mono<Void> deleteById(Long aLong);
}