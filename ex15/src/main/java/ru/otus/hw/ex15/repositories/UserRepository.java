package ru.otus.hw.ex15.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex15.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RepositoryRestResource(path = "person")
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    Optional<User> findById(Long aLong);

}