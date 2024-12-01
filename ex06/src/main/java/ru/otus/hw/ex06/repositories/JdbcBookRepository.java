package ru.otus.hw.ex06.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex06.models.Author;
import ru.otus.hw.ex06.models.Book;
import ru.otus.hw.ex06.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
//@AllArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final GenreRepository genreRepository;

    @PersistenceContext
    private final EntityManager em;

/*    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

 */

    // выбрать books_genres по книге
    private List<BookGenreRelation> findAllByBookId(Long id) {
/*
        Map<String, Object> params = Collections.singletonMap("book_id", id);
        return namedParameterJdbcOperations.query(
                "select book_id, genre_id from books_genres where book_id = :book_id",
                params,
                new BookGenreRowMapper());

 */
        return null;
    }

    // обновить книгу
    private void updateBook(Book book) {
/*
        // получить связи книги и жанров
        List<BookGenreRelation> bookGenre = findAllByBookId(book.getId());
        // получить список жанров у книги по идентификаторам жанров
        List<Genre> genres = genreRepository.findAllByIds(
                bookGenre
                        .stream()
                        .map(x -> x.getGenreId())
                        .collect(Collectors.toSet()));

        // обновить книгу полученными данными
        mergeBooksInfo(book, genres/*, bookGenre*/
        /*        );
         */
    }

    // обновить книгу
    private void updateBooks(List<Book> allSelectedBooks,
                             List<Genre> allSelectedGenres,
                             List<BookGenreRelation> allSelectedBookGenreRelations) {
        if (allSelectedBookGenreRelations == null) {
            return;
        }
        // пройтись по книгам
        for (Book book : allSelectedBooks) {
            List<Genre> genresForBook = new ArrayList<>();
            // подходящие жанры-книги для книги
            List<BookGenreRelation> bookGenresForBook =
                    allSelectedBookGenreRelations
                            .stream()
                            .filter(x -> x.getBookId() == book.getId())
                            .collect(Collectors.toList());

            // пройтись по отношениям жанры-книги и найти нужные жанры для кники
            for (BookGenreRelation bookGenreRelation : bookGenresForBook) {
                genresForBook.add(
                        allSelectedGenres
                                .stream()
                                .filter(x -> x.getId() == bookGenreRelation.getGenreId())
                                .findFirst()
                                .get()
                );
            }
            // жанры вписать в книгу
            mergeBooksInfo(book, genresForBook);
        }
    }

    @Override
    // поиск книги по идентификатору
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    // Найти все книги со всем
    public List<Book> findAll() {
        // все книги одним запросом
        System.out.println("все книги одним запросом");
        var books = getAllBooksWithoutGenres();

        // все отношения жанров и книг одним запросом
        System.out.println("все отношения жанров и книг одним запросом");
        var genreRelations = getAllGenreRelations();

        // все жанры одним запросом
        System.out.println("все жанры одним запросом");
        var genres = genreRepository.findAll();

        // соединение жанров с книгами
        System.out.println("соединение жанров с книгами");
        updateBooks(books, genres, genreRelations);

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
/*
        namedParameterJdbcOperations.update(
                "delete from books_genres where book_id = :book_id"
                , Map.of("book_id", id));

        namedParameterJdbcOperations.update(
                "delete from books where id = :id"
                , Map.of("id", id));

 */
    }

    private List<Book> getAllBooksWithoutGenres() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-genre-entity-graph");
        var query = em.createQuery("select distinct b from Book b left join fetch b.genres", Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
//        var query = em.createQuery("select b from Book b, Author a where a.id = b.author ", Book.class);
        return query.getResultList();

/*
        return jdbc.query(
                "select b.id, b.title, b.author_id, a.full_name "
                        + "from books b, authors a "
                        + "where a.id = b.author_id"
                , new BookRowMapper());
 */

    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setAuthor(
                    new Author(rs.getLong("author_id"), rs.getString("full_name")));

            book.setTitle(rs.getString("title"));
            book.setGenres(new ArrayList<>());

            return book;
        }
    }

    private List<BookGenreRelation> getAllGenreRelations() {
/*
        return jdbc.query(
                "select g.book_id, g.genre_id "
                        + "from books_genres g"
                , new GenreRelationsRowMapper());

 */
        return null;
    }

/*
    private static class GenreRelationsRowMapper implements RowMapper<BookGenreRelation> {
        @Override
        public BookGenreRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookGenreRelation bookGenreRelation = new BookGenreRelation();

            bookGenreRelation.setBookId(rs.getLong("book_id"));
            bookGenreRelation.setGenreId(rs.getLong("genre_id"));

            return bookGenreRelation;
        }
    }

 */

    private void mergeBooksInfo(Book booksWithoutGenres, List<Genre> genres) {
        List<Genre> genreList = new ArrayList<>();

        for (Genre genre : genres) {
            genreList.add(genre);
            booksWithoutGenres.setGenres(genreList);
        }
    }

    private Book insert(Book book) {
/*
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

 */
        return null;
    }

    private Book update(Book book) {
/*
        try {
            var keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcOperations.update(
                    "update books " +
                            " set title = :title, author_id = :author_id " +
                            " where id = :id"

                    , new MapSqlParameterSource(Map.of("id", book.getId(),
                            "title", book.getTitle(),
                            "author_id", book.getAuthor().getId()))
                    , keyHolder
                    , new String[]{"id"}
            );
            removeGenresRelationsFor(book);
            batchInsertGenresRelationsFor(book);
            return book;
        } catch (DataAccessException e) {
            System.out.println(e);
            throw new EntityNotFoundException(e.getMessage());
        }

 */
        return null;
    }

    private void batchInsertGenresRelationsFor(Book book) {
/*
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

 */
    }

    private void removeGenresRelationsFor(Book book) {
/*
        namedParameterJdbcOperations.update(
                "delete from books_genres where book_id = :book_id"
                , Map.of("book_id", book.getId()));

 */
    }


/*
    // Использовать для findById
    @SuppressWarnings("ClassCanBeRecord")
    @RequiredArgsConstructor
    private static class BookResultSetExtractor implements ResultSetExtractor<Book> {
        @Override
        public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
            return null;
        }
    }
    */

/*    private record BookGenreRelation(long bookId, long genreId) {
    }*/

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class BookGenreRelation {
        private long bookId;

        private long genreId;
    }

/*
    private static class BookGenreRowMapper implements RowMapper<BookGenreRelation> {
        @Override
        public BookGenreRelation mapRow(ResultSet rs, int i) throws SQLException {
            return new BookGenreRelation(
                    rs.getLong("book_id"),
                    rs.getLong("genre_id"));
        }
    }

 */
}


