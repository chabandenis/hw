package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex09.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);


}