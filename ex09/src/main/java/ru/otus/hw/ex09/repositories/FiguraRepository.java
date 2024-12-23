package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex09.models.Figura;

public interface FiguraRepository extends JpaRepository<Figura, Long> {
}