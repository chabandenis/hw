package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex09.models.PositionInChessFair;

import java.util.List;

public interface PositionInChessFairRepository extends JpaRepository<PositionInChessFair, Long> {
    // получить все фигуры на шахматной доске
    List<PositionInChessFair> findByChessFairId(Long id);
}