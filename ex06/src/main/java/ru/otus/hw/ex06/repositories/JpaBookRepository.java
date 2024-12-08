package ru.otus.hw.ex06.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex06.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {

    private final GenreRepository genreRepository;

    @PersistenceContext
    private final EntityManager em;

    @Override
    // поиск книги по идентификатору
    public Optional<Book> findById(long id) {
        System.out.println("Выбрать");

        EntityGraph graph = em.getEntityGraph("book-author-entity-graph");
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
        Optional<Book> retVal = Optional.ofNullable(em.find(Book.class, id, properties));

        System.out.println("Выбрал");
        return retVal;
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-genre-entity-graph");
        var query = em.createQuery("select distinct b " +
                        " from Book b left join fetch b.author "
                        + " left join fetch b.genres "
                , Book.class);

        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {

            System.out.println(" вставить");
            em.persist(book);
            System.out.println(" вставил");
            return book;
        }

        System.out.println(" обновить");
        Book retBook = em.merge(book);
        System.out.println(" обновил");
        return retBook;
    }

    @Override
    public void deleteById(long id) {
        em.remove(findById(id).get());
    }




}



