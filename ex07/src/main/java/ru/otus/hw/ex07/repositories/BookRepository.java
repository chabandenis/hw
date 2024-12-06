package ru.otus.hw.ex07.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex07.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genres" })
    Optional<Book> findById(long id);

    @EntityGraph(attributePaths = {"author", "genres" })
    List<Book> findAll();

    Book save(Book book);

    void deleteById(long id);

}
