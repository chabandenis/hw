package ru.otus.hw.ex11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex11.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);

    @Override
    Optional<User> findById(Long aLong);
}