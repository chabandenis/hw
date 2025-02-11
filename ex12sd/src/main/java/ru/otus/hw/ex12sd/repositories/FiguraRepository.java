package ru.otus.hw.ex12sd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex12sd.models.Figura;

public interface FiguraRepository extends JpaRepository<Figura, Long> {
}