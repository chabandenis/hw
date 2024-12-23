package ru.otus.hw.ex09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex09.models.ChessFair;

@Repository
public interface ChessFairRepository extends JpaRepository<ChessFair, Long> {


}