package ru.otus.hw.ex15.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.hw.ex15.models.ChessFair;

public interface ChessFairRepository extends ReactiveCrudRepository<ChessFair, Long> {


}