package ru.otus.hw.ex07.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.ex07.models.Author;
import ru.otus.hw.ex07.models.Book;
import ru.otus.hw.ex07.models.CommentBook;
import ru.otus.hw.ex07.models.Genre;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе DataJpa для работы с книгами ")
@DataJpaTest
@Import({JpaBookRepository.class, JpaGenreRepository.class, JpaAuthorRepository.class, JpaCommentBookRepository.class})
class JpaBookRepositoryTest {

    @Autowired
    private JpaBookRepository jpaBookRepository;

    @Autowired
    private JpaGenreRepository jpaGenreRepository;

    @Autowired
    private JpaAuthorRepository jpaAuthorRepository;

    @Autowired
    private JpaCommentBookRepository jpaCommentBookRepository;

    @Autowired
    private TestEntityManager em;

    private List<Author> dbAuthors;

    private List<Genre> dbGenres;

    private List<Book> dbBooks;

    private List<CommentBook> dbComments;

    private static List<Author> getDbAuthors() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Author(id, "Author_" + id))
                .toList();
    }

    private static List<Genre> getDbGenres() {
        return IntStream.range(1, 7).boxed()
                .map(id -> new Genre(id, "Genre_" + id))
                .toList();
    }

    private static List<CommentBook> getDbComments() {
        return IntStream.range(1, 7).boxed()
                .map(id -> new CommentBook(id, "comment " + id, new Book()))
                .toList();
    }


    private static List<Book> getDbBooks(List<Author> dbAuthors,
                                         List<Genre> dbGenres,
                                         List<CommentBook> dbComments) {
        List<Book> retBooks =
                IntStream.range(1, 4).boxed()
                        .map(id ->
                                new Book(id,
                                        "BookTitle_" + id,
                                        dbAuthors.get(id - 1),
                                        dbGenres.subList((id - 1) * 2, (id - 1) * 2 + 2),
                                        dbComments.subList((id - 1) * 2, (id - 1) * 2 + 2)
                                ))
                        .toList();

        for (Book book : retBooks) {
            for (CommentBook commentBook : book.getCommentBook()) {
                commentBook.setBook(book);
            }
        }

        return retBooks;
    }

    private static List<Book> getDbBooks() {
        var dbAuthors = getDbAuthors();
        var dbGenres = getDbGenres();
        var dbComments = getDbComments();

        return getDbBooks(dbAuthors, dbGenres, dbComments);
    }

    @BeforeEach
    void setUp() {
        dbAuthors = getDbAuthors();
        dbGenres = getDbGenres();
        dbComments = getDbComments();
        dbBooks = getDbBooks(dbAuthors, dbGenres, dbComments);
    }

    @DisplayName("должен загружать книгу по id")
    @ParameterizedTest
    @MethodSource("getDbBooks")
    void shouldReturnCorrectBookById(Book expectedBook) {
        var actualBook = jpaBookRepository.findById(expectedBook.getId());
        System.out.println("actualBook " + actualBook.get().getAuthor().getFullName());
        assertThat(actualBook).isPresent()
                .get()
                .isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг")
    @Test
    void shouldReturnCorrectBooksList() {

        var actualBooks = jpaBookRepository.findAll();
        var expectedBooks = dbBooks;

        assertThat(actualBooks).containsExactlyElementsOf(expectedBooks);
        actualBooks.forEach(System.out::println);
    }

    @DisplayName("должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        var expectedBook = new Book(
                0,
                "BookTitle_10500",
                dbAuthors.get(0),
                List.of(dbGenres.get(0), dbGenres.get(2)),
                List.of()
        );
        var returnedBook = jpaBookRepository.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(jpaBookRepository.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен сохранять измененную книгу")
    @Test
    void shouldSaveUpdatedBook() {
        var expectedBook = new Book(
                1L,
                "BookTitle_10500",
                dbAuthors.get(2),
                List.of(dbGenres.get(4), dbGenres.get(5)),
                List.of()
        );

        assertThat(jpaBookRepository.findById(expectedBook.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedBook);

        var returnedBook = jpaBookRepository.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(jpaBookRepository.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteBook() {
        assertThat(jpaBookRepository.findById(1L)).isPresent();
        jpaBookRepository.deleteById(1L);
        assertThat(jpaBookRepository.findById(1L)).isEmpty();
    }
}