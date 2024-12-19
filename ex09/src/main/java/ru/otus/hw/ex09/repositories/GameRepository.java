package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex09.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    @EntityGraph(value = "game-graph")
    List<Game> findByUserBlackId(Long id);

    @EntityGraph(value = "game-graph")
    List<Game> findByUserWhiteId(Long id);

    @EntityGraph(value = "game-graph")
    @Override
    Optional<Game> findById(Long aLong);


}