package ru.otus.hw.ex05.repositories;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex05.models.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class JdbcBookGenreRepository implements BookGenreRepository {

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<BookGenre> findAllByBookId(Long id) {
        Map<String, Object> params = Collections.singletonMap("book_id", id);
        return namedParameterJdbcOperations.query(
                "select book_id, genre_id from books_genres where book_id = :book_id",
                params,
                new JdbcBookGenreRepository.BookGenreRowMapper());
    }

    private static class BookGenreRowMapper implements RowMapper<BookGenre> {
        @Override
        public BookGenre mapRow(ResultSet rs, int i) throws SQLException {
            return new BookGenre(
                    rs.getLong("book_id"),
                    rs.getLong("genre_id"));
        }
    }
}
