package ru.otus.hw.ex06.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex06.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {

    private final GenreRepository genreRepository;

    @PersistenceContext
    private final EntityManager em;

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
        Optional<Book> retVal = Optional.ofNullable(em.find(Book.class, id));
        System.out.println("Выбрал");
        return retVal;
    }

    private List<Book> mergeBooks(List<Book> booksWithGenre, List<Book> booksWithComments) {
        Map<Long, Book> bookWithGenreMap = booksWithGenre.stream().collect(Collectors.toMap(Book::getId, book -> book));
        Map<Long, Book> bookWithCommentsMap = booksWithComments.stream().collect(Collectors.toMap(Book::getId, book -> book));

        List<Book> result = new ArrayList<>();

        for (Long pos : bookWithGenreMap.keySet()) {
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
}



