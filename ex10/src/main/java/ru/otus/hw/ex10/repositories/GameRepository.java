package ru.otus.hw.ex10.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.hw.ex10.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("""
            select g from Game g
            where (g.userWhite.id = :user1 and g.userBlack.id = :user2 ) or 
                        (g.userWhite.id = :user2 and g.userBlack.id = :user1)
            order by g.dateGame DESC""")
    List<Game> findByUserId1AndUserId2OrderByDateGameDesc(
            @Param("user1") Long user1,
            @Param("user2") Long user2);

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