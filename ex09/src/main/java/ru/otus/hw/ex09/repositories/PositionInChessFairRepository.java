package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex09.models.PositionInChessFair;

import java.util.List;

public interface PositionInChessFairRepository extends JpaRepository<PositionInChessFair, Long> {
    // получить все фигуры на шахматной доске
    @EntityGraph(value = "position-in-chess-fair-graph")
    List<PositionInChessFair> findByChessFairId(Long id);
}