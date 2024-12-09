package ru.otus.hw.ex06.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex06.models.Book;
import ru.otus.hw.ex06.models.Comment;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class JpaCommentRepository implements CommentRepository {

    private final Logger logger = LoggerFactory.getLogger(JpaCommentRepository.class);

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findCommentByBookId(long bookId) {
        logger.debug("выбрать");
        TypedQuery<Comment> query = em.createQuery(
                "select c " +
                        "from Comment c " +
                        "where c.book.id = :p1"
                , Comment.class);
        query.setParameter("p1", bookId);
        logger.debug("выбрали");
        return query.getResultList();
    }

    @Override
    public Comment create(long bookId, String comment) {
        Book book = em.find(Book.class, bookId);

        if (book == null) {
            return null;
        }

        Comment commentObj = new Comment(0, comment, book);

        em.persist(commentObj);

        return commentObj;
    }

    @Override
    public Comment update(long commentId, long bookId, String comment) {
        Book book = em.find(Book.class, bookId);
        if (book == null) {
            return null;
        }

        Comment commentInDb = em.find(Comment.class, commentId);
        if (commentInDb == null) {
            return null;
        }

        if (commentInDb.getBook().getId() != bookId) {
            commentInDb.setBook(book);
        }

        commentInDb.setText(comment);

        em.merge(commentInDb);

        return commentInDb;
    }

    @Override
    public void deleteById(long id) {
        Comment comment = em.find(Comment.class, id);
        if (comment != null) {
            em.remove(comment);
        }
    }

    @Override
    public void deleteByBookId(long bookId) {
        List<Comment> comments = findCommentByBookId(bookId);
        for (Comment comment : comments) {
            logger.debug("удаляю id: " + comment.getId());
            em.remove(comment);
            logger.debug("удален");
        }
    }
}
