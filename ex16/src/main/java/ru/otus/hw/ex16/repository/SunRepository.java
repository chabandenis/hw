package ru.otus.hw.ex16.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex16.model.Sun;

@Repository
public interface SunRepository extends CrudRepository<Sun, String> {
}
