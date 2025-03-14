package ru.otus.hw.ex17_game.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_game.models.PositionInChessFair;

public interface PositionInChessFairRepository extends ReactiveCrudRepository<PositionInChessFair, Long> {

    // получить фигуры на шахматной доске
    Flux<PositionInChessFair> findByChessFairId(Long chessFairId);

    Mono<Long> deleteByChessFairId(Long chessFairId);

}