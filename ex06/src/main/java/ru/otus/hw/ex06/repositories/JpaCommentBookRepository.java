package ru.otus.hw.ex06.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex06.models.Comment;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class JpaCommentBookRepository implements CommentBookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findCommentByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery(
                "select c " +
                        "from Comment c " +
                        "where c.book.id = :p1"
                , Comment.class);
        query.setParameter("p1", bookId);
        return query.getResultList();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        Comment comment = findById(id).get();
        em.remove(comment);
    }

    @Override
    public void deleteByBookId(long bookId) {
        List<Comment> comments = findCommentByBookId(bookId);
        for (Comment comment : comments) {
            System.out.println("удаляю id: " + comment.getId());
            em.remove(comment);
            System.out.println("удален");
        }
    }
}
