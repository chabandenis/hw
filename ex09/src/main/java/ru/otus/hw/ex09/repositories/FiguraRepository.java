package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex09.models.Figura;

@Repository
public interface FiguraRepository extends JpaRepository<Figura, Long> {
}