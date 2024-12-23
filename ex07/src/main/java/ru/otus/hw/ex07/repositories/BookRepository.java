package ru.otus.hw.ex07.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex07.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre-entity-graph")
    Optional<Book> findById(Long id);

    @Override
    @EntityGraph(value = "book-author-genre-entity-graph")
    List<Book> findAll();

    void deleteById(Long id);

}
