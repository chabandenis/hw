package ru.otus.hw.ex11.repositories;

//import org.springframework.data.jpa.repository.EntityGraph;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.hw.ex11.models.PositionInChessFair;

public interface PositionInChessFairRepository extends ReactiveCrudRepository<PositionInChessFair, Long> {

//    long deleteByChessFair(ChessFair chessFair);

    // получить все фигуры на шахматной доске
//    @EntityGraph(value = "position-in-chess-fair-graph")
//    List<PositionInChessFair> findByChessFairId(Long id);


}