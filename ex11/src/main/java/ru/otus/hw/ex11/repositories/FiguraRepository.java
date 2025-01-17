package ru.otus.hw.ex11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex11.models.Figura;

public interface FiguraRepository extends JpaRepository<Figura, Long> {
}