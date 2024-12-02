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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
//@AllArgsConstructor
public class JpaBookRepository implements BookRepository {

    private final GenreRepository genreRepository;

    @PersistenceContext
    private final EntityManager em;

    //private final BookRepository bookRepository;

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


    @Override
    // поиск книги по идентификатору
/*
Выбрать
Hibernate: select b1_0.id,b1_0.author_id,b1_0.title from books b1_0 where b1_0.id=?
Выбрал
Hibernate: select cb1_0.book_id,cb1_0.id,cb1_0.text from comments cb1_0 where cb1_0.book_id=?
Hibernate: select a1_0.id,a1_0.full_name from authors a1_0 where a1_0.id=?
Hibernate: select g1_0.book_id,g1_1.id,g1_1.name from books_genres g1_0 join genres g1_1 on g1_1.id=g1_0.genre_id where g1_0.book_id=?
Id: 1, title: BookTitle_1, author: {Id: 1, FullName: Author_1}, genres: [{Id: 1, Name: Genre_1}, {Id: 2, Name: Genre_2}], comments: [Id: 1, BookId: 1, Text: comment 1, Id: 2, BookId: 1, Text: comment 2]
*/
    public Optional<Book> findById(long id) {
        System.out.println("Выбрать");
        Optional<Book> retVal =  Optional.ofNullable(em.find(Book.class, id));
        System.out.println("Выбрал");
        return retVal;
    }

    private List<Book> mergeBooks(List<Book> booksWithGenre, List<Book> booksWithComments){
        Map<Long, Book> bookWithGenreMap =  booksWithGenre.stream().collect(Collectors.toMap(Book::getId, book->book));
        Map<Long, Book> bookWithCommentsMap =  booksWithComments.stream().collect(Collectors.toMap(Book::getId, book->book));

        List<Book> result = new ArrayList<>();

        for (Long pos : bookWithGenreMap.keySet())
        {
            Book tmpBook = bookWithGenreMap.get(pos);
            tmpBook.setCommentBook(bookWithCommentsMap.get(pos).getCommentBook());
            result.add(tmpBook);
        }

        return result;
    }

    @Override
    // Найти все книги со всеми внутренностями

    // по следам разбора ошибки
    // https://struchkov.dev/blog/ru/hibernate-multiple-bag-fetch-exception/
    // hibernate не может выбрать несколько списков без ошибки.
    /*
        Результат одним запросом не получился, потрачен рабочий день, но без шансов.
        Пробовал разные способы, наверное так оптимальнее, если пользоваться hibernate c автоматической обработкой

        Hibernate: select b1_0.id,a1_0.id,a1_0.full_name,g1_0.book_id,g1_1.id,g1_1.name,b1_0.title from books b1_0 left join authors a1_0 on a1_0.id=b1_0.author_id left join books_genres g1_0 on b1_0.id=g1_0.book_id left join genres g1_1 on g1_1.id=g1_0.genre_id
        Hibernate: select b1_0.id,a1_0.id,a1_0.full_name,cb1_0.book_id,cb1_0.id,cb1_0.text,b1_0.title from books b1_0 left join authors a1_0 on a1_0.id=b1_0.author_id left join comments cb1_0 on b1_0.id=cb1_0.book_id
        Id: 1, title: BookTitle_1, author: {Id: 1, FullName: Author_1}, genres: [{Id: 1, Name: Genre_1}, {Id: 2, Name: Genre_2}], comments: [Id: 1, BookId: 1, Text: comment 1, Id: 2, BookId: 1, Text: comment 2],
        Id: 2, title: BookTitle_2, author: {Id: 2, FullName: Author_2}, genres: [{Id: 3, Name: Genre_3}, {Id: 4, Name: Genre_4}], comments: [Id: 3, BookId: 2, Text: comment 3, Id: 4, BookId: 2, Text: comment 4],
        Id: 3, title: BookTitle_3, author: {Id: 3, FullName: Author_3}, genres: [{Id: 5, Name: Genre_5}, {Id: 6, Name: Genre_6}], comments: [Id: 5, BookId: 3, Text: comment 5, Id: 6, BookId: 3, Text: comment 6]
     */
    public List<Book> findAll() {
        // все книги одним запросом (в реальности двумя запросами)
        System.out.println("все книги одним запросом");
        var booksWithGenre = getAllBooksWithGenres();

        var booksWithComment = getAllBooksWithComment();

        List<Book> result = mergeBooks(booksWithGenre, booksWithComment);

        return result;
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
/*
        namedParameterJdbcOperations.update(
                "delete from books_genres where book_id = :book_id"
                , Map.of("book_id", id));

        namedParameterJdbcOperations.update(
                "delete from books where id = :id"
                , Map.of("id", id));

 */
    }

    private List<Book> getAllBooksWithGenres() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-genre-entity-graph");
        var query = em.createQuery("select  b " +
                        " from Book b left join fetch b.author "
                        + " left join fetch b.genres "
                , Book.class);

        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    private List<Book> getAllBooksWithComment() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-comment-entity-graph");
        var query = em.createQuery("select b " +
                        " from Book b "
                        + " left join fetch b.commentBook "
                , Book.class);

        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
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

    private void mergeBooksInfo(Book booksWithoutGenres, List<Genre> genres) {
        List<Genre> genreList = new ArrayList<>();

        for (Genre genre : genres) {
            genreList.add(genre);
            booksWithoutGenres.setGenres(genreList);
        }
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



