package ru.otus.hw.ex17_user.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.otus.hw.ex17_user.models.Game;

import java.util.Collection;

public interface GameRepository extends ReactiveCrudRepository<Game, Long> {
    Flux<Game> findByUserWhiteIdInAndUserBlackIdInOrderByDateGameDesc(
            Collection<Long> userWhiteIds,
            Collection<Long> userBlackIds);
}
