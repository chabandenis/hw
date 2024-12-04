package ru.otus.hw.ex07.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex07.models.CommentBook;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class JpaCommentBookRepository implements CommentBookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<CommentBook> findById(long id) {
        return Optional.ofNullable(em.find(CommentBook.class, id));
    }

    @Override
    public List<CommentBook> findCommentByBookId(long bookId) {
        TypedQuery<CommentBook> query = em.createQuery(
                "select c " +
                        "from CommentBook c " +
                        "where c.book.id = :p1"
                , CommentBook.class);
        query.setParameter("p1", bookId);
        return query.getResultList();
    }

    @Override
    public CommentBook save(CommentBook commentBook) {
        if (commentBook.getId() == 0) {
            em.persist(commentBook);
            return commentBook;
        }
        return em.merge(commentBook);
    }

    @Override
    public void deleteById(long id) {
        CommentBook commentBook = findById(id).get();
        em.remove(commentBook);
    }

    @Override
    public void deleteByBookId(long bookId) {
        List<CommentBook> commentBooks = findCommentByBookId(bookId);
        for (CommentBook commentBook : commentBooks) {
            System.out.println("удаляю id: " + commentBook.getId());
            em.remove(commentBook);
            System.out.println("удален");
        }
    }
}
