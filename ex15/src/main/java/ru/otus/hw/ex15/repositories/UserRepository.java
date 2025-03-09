package ru.otus.hw.ex15.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex15.models.User;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    Optional<User> findById(Integer integer);

    @Override
    List<User> findAll();

}