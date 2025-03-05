package ru.otus.hw.ex16.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex16.model.Caterpillar;

@Repository
public interface CaterpillarRepository extends CrudRepository<Caterpillar, String> {
}
