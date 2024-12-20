package ru.otus.hw.ex06.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.ex06.exceptions.EntityNotFoundException;
import ru.otus.hw.ex06.models.Author;
import ru.otus.hw.ex06.models.Book;
import ru.otus.hw.ex06.models.Comment;
import ru.otus.hw.ex06.models.Genre;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@DisplayName("Репозиторий на основе DataJpa для работы с книгами ")
@DataJpaTest
@Import({JpaBookRepository.class, JpaGenreRepository.class, JpaAuthorRepository.class, JpaCommentRepository.class})
class JpaBookRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(JpaBookRepositoryTest.class);

    @Autowired
    private JpaBookRepository jpaBookRepository;

    @Autowired
    private JpaAuthorRepository jpaAuthorRepository;

    @Autowired
    private JpaGenreRepository jpaGenreRepository;


    @Autowired
    private TestEntityManager em;

    private List<Author> dbAuthors;

    private List<Genre> dbGenres;

    private List<Book> dbBooks;

    private List<Comment> dbComments;

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

    private static List<Comment> getDbComments() {
        return IntStream.range(1, 7).boxed()
                .map(id -> new Comment(id, "comment " + id, new Book()))
                .toList();
    }


    private static List<Book> getDbBooks(List<Author> dbAuthors,
                                         List<Genre> dbGenres,
                                         List<Comment> dbComments) {
        List<Book> retBooks =
                IntStream.range(1, 4).boxed()
                        .map(id ->
                                new Book(id,
                                        "BookTitle_" + id,
                                        dbAuthors.get(id - 1),
                                        dbGenres.subList((id - 1) * 2, (id - 1) * 2 + 2)/*,
                                        dbComments.subList((id - 1) * 2, (id - 1) * 2 + 2)*/
                                ))
                        .toList();

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
        logger.debug("actualBook " + actualBook.get().getAuthor().getFullName());
        assertThat(actualBook).isPresent()
                .get()
                .usingRecursiveComparison()
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
                List.of(dbGenres.get(0), dbGenres.get(2))
        );
        var returnedBook = jpaBookRepository.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(jpaBookRepository.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен сохранять измененную книгу")
    @Test
    void shouldSaveUpdatedBook() {
        var expectedBook = new Book(
                1L,
                "BookTitle_10500",
                dbAuthors.get(2),
                List.of(dbGenres.get(4), dbGenres.get(5))
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
                .usingRecursiveComparison()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteBook() {
        assertThat(jpaBookRepository.findById(1L)).isPresent();
        jpaBookRepository.deleteById(1L);
        assertThat(jpaBookRepository.findById(1L)).isEmpty();
    }

    // создать книгу
    @Test
    void create() {
        String title = "AAA";
        var genres = Set.of(1l, 2l);
        long authorId = 1;

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(
                        () -> jpaBookRepository.create(title, 100, Set.of())
                )
                .withMessage("Отсутствует автор с ID=100");

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(
                        () -> jpaBookRepository.create(title, authorId, Set.of(100l))
                )
                .withMessage("Отсутствует жанр с id=100");

        Book bookCreated = jpaBookRepository.create(title, authorId, genres);

        Optional<Book> bookFromDb = jpaBookRepository.findById(bookCreated.getId());

        assertThat(bookFromDb).isPresent();

        assertThat(bookFromDb.get().getTitle()).isEqualTo(title);

        var authorInDb = jpaAuthorRepository.findById(authorId);

        assertThat(authorInDb).isPresent();

        assertThat(bookFromDb.get().getAuthor()).isEqualTo(authorInDb.get());


        List<Genre> genresSort = bookFromDb.get().getGenres();
        Collections.sort(
                genresSort,
                new Comparator<Genre>() {
                    @Override
                    public int compare(Genre o1, Genre o2) {
                        return Long.compare(o1.getId(), o2.getId());
                    }
                });

        List<Genre> genreSortExpected = jpaGenreRepository.findAllByIds(genres);
        Collections.sort(
                genreSortExpected,
                new Comparator<Genre>() {
                    @Override
                    public int compare(Genre o1, Genre o2) {
                        return Long.compare(o1.getId(), o2.getId());
                    }
                });

        assertThat(genresSort)
                .usingRecursiveComparison()
                .isEqualTo(genreSortExpected);

        // в исходное состояние
        jpaBookRepository.deleteById(bookCreated.getId());

    }

    // удалить книгу
    @Test
    void update() {
        String title = "AAA";
        var genres = Set.of(1l, 2l);
        long authorId = 1;
        long bookId = 1;

        var bookInDbBefore = jpaBookRepository.findById(bookId);
        assertThat(bookInDbBefore).isPresent();

        var bookAfterUpdate = jpaBookRepository.update(bookId, title, authorId, genres);

        var authorInDb = jpaAuthorRepository.findById(authorId);
        assertThat(authorInDb).isPresent();

        assertThat(bookAfterUpdate.getAuthor()).isEqualTo(authorInDb.get());
        assertThat(bookAfterUpdate.getTitle()).isEqualTo(title);

        List<Genre> genresSort = bookAfterUpdate.getGenres();
        Collections.sort(
                genresSort,
                new Comparator<Genre>() {
                    @Override
                    public int compare(Genre o1, Genre o2) {
                        return Long.compare(o1.getId(), o2.getId());
                    }
                });

        List<Genre> genreSortExpected = jpaGenreRepository.findAllByIds(genres);
        Collections.sort(
                genreSortExpected,
                new Comparator<Genre>() {
                    @Override
                    public int compare(Genre o1, Genre o2) {
                        return Long.compare(o1.getId(), o2.getId());
                    }
                });

        assertThat(genresSort)
                .usingRecursiveComparison()
                .isEqualTo(genreSortExpected);

        // в исходное состояние
        jpaBookRepository.update(
                bookInDbBefore.get().getId(),
                bookInDbBefore.get().getTitle(),
                bookInDbBefore.get().getAuthor().getId(),
                bookInDbBefore.get().getGenres()
                        .stream()
                        .map(x -> x.getId())
                        .collect(Collectors.toSet())
        );
    }
}