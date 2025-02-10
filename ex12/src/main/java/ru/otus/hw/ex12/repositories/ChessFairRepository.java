package ru.otus.hw.ex12.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.hw.ex12.models.ChessFair;

public interface ChessFairRepository extends ReactiveCrudRepository<ChessFair, Long> {


}