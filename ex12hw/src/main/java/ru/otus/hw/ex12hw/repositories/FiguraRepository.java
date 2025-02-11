package ru.otus.hw.ex12hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex12hw.models.Figura;

public interface FiguraRepository extends JpaRepository<Figura, Long> {
}