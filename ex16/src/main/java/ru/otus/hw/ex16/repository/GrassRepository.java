package ru.otus.hw.ex16.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex16.model.Egg;
import ru.otus.hw.ex16.model.Grass;

@Repository
public interface GrassRepository extends CrudRepository<Grass, String> {
}
