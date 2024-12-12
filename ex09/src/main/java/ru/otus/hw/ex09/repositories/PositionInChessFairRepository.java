package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex09.models.PositionInChessFair;

public interface PositionInChessFairRepository extends JpaRepository<PositionInChessFair, Long> {
}