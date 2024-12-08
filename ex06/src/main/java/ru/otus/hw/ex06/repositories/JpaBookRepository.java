package ru.otus.hw.ex06.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex06.models.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {

    private final GenreRepository genreRepository;

    @PersistenceContext
    private final EntityManager em;

    Logger logger = LoggerFactory.getLogger(JpaBookRepository.class);

    @Override
    // поиск книги по идентификатору
    public Optional<Book> findById(long id) {
        logger.info("Выбрать");

        EntityGraph graph = em.getEntityGraph("book-author-entity-graph");
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
        Optional<Book> retVal = Optional.ofNullable(em.find(Book.class, id, properties));

        logger.info("Выбрал");
        return retVal;
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-entity-graph");
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

            logger.info(" вставить");
            em.persist(book);
            logger.info(" вставил");
            return book;
        }

        logger.info(" обновить");
        Book retBook = em.merge(book);
        logger.info(" обновил");
        return retBook;
    }

    @Override
    public void deleteById(long id) {
        em.remove(findById(id).get());
    }


}
