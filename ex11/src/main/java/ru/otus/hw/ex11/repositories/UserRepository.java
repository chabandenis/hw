package ru.otus.hw.ex11.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex11.models.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByLoginAndPassword(String login, String password);

    Mono<User> findByLogin(String login);

    Mono<User> findById(Long aLong);
}