package ru.otus.hw.ex11.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.hw.ex11.models.ChessFair;

public interface ChessFairRepository extends ReactiveCrudRepository<ChessFair, Long> {


}