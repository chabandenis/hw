package ru.otus.hw.ex17_game.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_game.models.User;

import java.util.Collection;


public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> findByIdIn(Collection<Long> ids);

    Mono<User> findByLoginAndPassword(String login, String password);

    Mono<User> findByLogin(String login);

    Mono<User> findById(Long aLong);

    @Override
    Mono<Void> deleteById(Long id);


}