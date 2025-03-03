package ru.otus.hw.ex16.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex16.model.Egg;

@Repository
public interface SunRepository extends CrudRepository<Egg, String> {
}
