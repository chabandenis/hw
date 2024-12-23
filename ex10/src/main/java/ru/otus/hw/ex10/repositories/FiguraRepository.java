package ru.otus.hw.ex10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex10.models.Figura;

public interface FiguraRepository extends JpaRepository<Figura, Long> {
}