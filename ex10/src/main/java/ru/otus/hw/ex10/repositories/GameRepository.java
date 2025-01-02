package ru.otus.hw.ex10.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.hw.ex10.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    @EntityGraph(value = "game-graph")
    @Query("select g from Game g where g.userBlack.id = :id or g.userWhite.id = :id order by g.id DESC")
    List<Game> findByUserBlackIdOrUserWhiteIdOrderByIdDesc(@Param("id") Long id);




    @EntityGraph(value = "game-graph")
    List<Game> findByUserBlackId(Long id);

    @EntityGraph(value = "game-graph")
    List<Game> findByUserWhiteId(Long id);

    @EntityGraph(value = "game-graph")
    @Override
    Optional<Game> findById(Long aLong);


}