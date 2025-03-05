package ru.otus.hw.ex16.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex16.model.Pupae;

@Repository
public interface PupaeRepository extends CrudRepository<Pupae, String> {
}