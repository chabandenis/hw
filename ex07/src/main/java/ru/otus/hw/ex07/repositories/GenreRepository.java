package ru.otus.hw.ex07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex07.models.Genre;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByIdIn(Collection<Long> ids);

    List<Genre> findAll();

}
