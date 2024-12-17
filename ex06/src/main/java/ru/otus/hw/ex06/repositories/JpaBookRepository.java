package ru.otus.hw.ex06.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex06.exceptions.EntityNotFoundException;
import ru.otus.hw.ex06.models.Author;
import ru.otus.hw.ex06.models.Book;
import ru.otus.hw.ex06.models.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(JpaBookRepository.class);


    @Override
    // поиск книги по идентификатору
    public Optional<Book> findById(long id) {
        logger.debug("Выбрать");

        EntityGraph graph = em.getEntityGraph("book-author-genre-entity-graph");
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
        Optional<Book> retVal = Optional.ofNullable(em.find(Book.class, id, properties));

        logger.debug("Выбрал");
        return retVal;
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-entity-graph");
        var query = em.createQuery("select distinct b " +
                        " from Book b"
                , Book.class);

        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {

            logger.debug(" вставить");
            em.persist(book);
            logger.debug(" вставил");
            return book;
        }

        logger.debug(" обновить");
        Book retBook = em.merge(book);
        logger.debug(" обновил");
        return retBook;
    }

    @Override
    public Book create(String title, long authorId, Set<Long> genres) {
        Book book = new Book();
        book.setTitle(title);
        Author author = em.find(Author.class, authorId);
        if (author == null) {
            throw new EntityNotFoundException("Отсутствует автор с ID=" + authorId);
        }
        book.setAuthor(author);
        List<Genre> genreList = new ArrayList<>();
        for (Long genreId : genres) {
            Genre genre = em.find(Genre.class, genreId);
            if (genre == null) {
                throw new EntityNotFoundException("Отсутствует жанр с id=" + genreId);
            }
            genreList.add(genre);
        }
        book.setGenres(genreList);
        em.persist(book);
        return book;
    }

    @Override
    public Book update(Long bookId, String title, long authorId, Set<Long> genres) {
        Book book = em.find(Book.class, bookId);
        if (book == null) {
            throw new EntityNotFoundException("В БД отстутсвет книга с ID=" + bookId);
        }
        book.setTitle(title);
        Author author = em.find(Author.class, authorId);
        if (author == null) {
            throw new EntityNotFoundException("Отсутствует автор с ID=" + authorId);
        }
        List<Genre> genreList = new ArrayList<>();
        for (Long genreId : genres) {
            Genre genre = em.find(Genre.class, genreId);
            if (genre == null) {
                throw new EntityNotFoundException("Отсутствует жанр с id=" + genreId);
            }
            genreList.add(genre);
        }
        book.setGenres(genreList);
        Book bookMerge = em.merge(book);
        return bookMerge;
    }


    @Override
    public void deleteById(long id) {
        var bookInDb = em.find(Book.class, id);

        if (bookInDb == null) {
            throw new EntityNotFoundException("В БД отсутствует зазпись книги с ID=" + id);
        }

        em.remove(bookInDb);
    }

}
