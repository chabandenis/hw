package ru.otus.hw.ex05.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex05.models.Author;
import ru.otus.hw.ex05.models.Book;
import ru.otus.hw.ex05.models.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
//@AllArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final GenreRepository genreRepository;

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final JdbcBookGenreRepository jdbcBookGenreRepository;

    private void updateBook(Book book) {
        var bookGenre = jdbcBookGenreRepository.findAllByBookId(book.getId());
        List<Genre> genres = genreRepository.findAllByIds(
                bookGenre
                        .stream()
                        .map(x -> x.getGenreId())
                        .collect(Collectors.toSet()));

        mergeBooksInfo(book, genres);
    }

    @Override
    public Optional<Book> findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        var book = namedParameterJdbcOperations.queryForObject(
                "select b.id, b.title, b.author_id, a.full_name "
                        + "from books b, authors a "
                        + "where a.id = :id and a.id = b.author_id"
                , params
                , new JdbcBookRepository.BookRowMapper());

        updateBook(book);

        return Optional.of(book);
    }

    @Override
    public List<Book> findAll() {
        var books = getAllBooksWithoutGenres();

        for (Book book : books) {
            updateBook(book);
        }

        return books;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        //...
    }

    private List<Book> getAllBooksWithoutGenres() {
        return jdbc.query(
                "select b.id, b.title, b.author_id, a.full_name "
                        + "from books b, authors a "
                        + "where a.id = b.author_id"
                , new JdbcBookRepository.BookRowMapper());
    }

    private List<BookGenreRelation> getAllGenreRelations() {
        return new ArrayList<>();
    }

    private void mergeBooksInfo(Book booksWithoutGenres, List<Genre> genres) {

        List<Genre> genreList = new ArrayList<>();

        for (Genre genre : genres) {
            genreList.add(genre);
        }

        booksWithoutGenres.setGenres(genreList);
    }


    private void mergeBooksInfo(List<Book> booksWithoutGenres, List<Genre> genres,
                                List<BookGenreRelation> relations) {
        // Добавить книгам (booksWithoutGenres) жанры (genres) в соответствии со связями (relations)
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into books (title, author_id) values (:title, :author_id)"
                , new MapSqlParameterSource(Map.of("title", book.getTitle(),
                        "author_id", book.getAuthor().getId()
                ))
                , keyHolder
                , new String[]{"id"}
        );

        book.setId(keyHolder.getKeyAs(Long.class));
        batchInsertGenresRelationsFor(book);
        return book;
    }

    private Book update(Book book) {
        //...

        // Выбросить EntityNotFoundException если не обновлено ни одной записи в БД
        removeGenresRelationsFor(book);
        batchInsertGenresRelationsFor(book);

        return book;
    }

    private void batchInsertGenresRelationsFor(Book book) {
        // Использовать метод batchUpdate
        jdbc.batchUpdate(
                "insert into books_genres (book_id, genre_id) values (?, ?)"
                , new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setLong(1, book.getId());
                        preparedStatement.setLong(2, book.getGenres().get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return book.getGenres().size();
                    }
                });
    }

    private void removeGenresRelationsFor(Book book) {
        //...
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();

            book.setId(rs.getLong("id"));
            book.setAuthor(new Author(rs.getLong("author_id"), rs.getString("full_name")));
            book.setTitle(rs.getString("title"));
            book.setGenres(new ArrayList<>());

            return book;
        }
    }


    // Использовать для findById
    @SuppressWarnings("ClassCanBeRecord")
    @RequiredArgsConstructor
    private static class BookResultSetExtractor implements ResultSetExtractor<Book> {

        @Override
        public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
            return null;
        }
    }

    private record BookGenreRelation(long bookId, long genreId) {
    }
}
