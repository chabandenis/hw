package ru.otus.hw.ex17_game.repositories.game;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_game.models.Game;

import java.util.Collection;

public interface GameRepository extends ReactiveCrudRepository<Game, Long> {

    Flux<Game> findByUserWhiteIdInAndUserBlackIdInOrderByDateGameDesc(
            Collection<Long> userWhiteIds,
            Collection<Long> userBlackIds);

    @Override
    Mono<Void> deleteById(Long id);
}