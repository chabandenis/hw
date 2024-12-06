package ru.otus.hw.ex07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex07.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
