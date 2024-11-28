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
public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public Optional<Comment> findById(long id) {
        return Optional.empty();
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
    public Comment save(Comment commentDto) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void deleteByBookId(long bookId) {

    }




/*
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Author> findAll() {
        return namedParameterJdbcOperations.query("select id, full_name from authors", new AuthorRowMapper());
    }

    @Override
    public Optional<Author> findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return Optional.ofNullable((Author) namedParameterJdbcOperations.query(
                "select id, full_name from authors where id = :id", params, new AuthorRowMapper()));
    }

    @Override
    public List<Comment> findByBookId(long bookId) {
        return List.of();
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void deleteByBookId(long bookId) {

    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return new Author(rs.getLong("id"),
                    rs.getString("full_name"));
        }
    }

 */
}
