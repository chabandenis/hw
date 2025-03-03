package ru.otus.hw.ex16.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex16.model.Egg;

@Repository
public interface ButterflyRepository extends CrudRepository<Egg, String> {
}
