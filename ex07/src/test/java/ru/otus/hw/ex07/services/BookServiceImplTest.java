package ru.otus.hw.ex07.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex07.converters.AuthorConverter;
import ru.otus.hw.ex07.converters.BookConverter;
import ru.otus.hw.ex07.converters.CommentConverter;
import ru.otus.hw.ex07.converters.GenreConverter;
import ru.otus.hw.ex07.dto.AuthorDto;
import ru.otus.hw.ex07.dto.BookDto;
import ru.otus.hw.ex07.dto.GenreDto;
import ru.otus.hw.ex07.models.Author;
import ru.otus.hw.ex07.models.Book;
import ru.otus.hw.ex07.models.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BookServiceImpl.class,
        BookConverter.class,
        AuthorConverter.class,
        GenreConverter.class,
        BookConverter.class,
        CommentServiceImpl.class,
        CommentConverter.class

})

@Transactional(propagation = Propagation.NEVER)
class BookServiceImplTest {

    private final static long BOOK_ID_NOT_EXISTS = 33;
    private final static long BOOK_ID_EXISTS = 1;

    @Autowired
    private BookServiceImpl bookService;

    private List<BookDto> expectedBook;

    @BeforeEach
    void setUp() {
        expectedBook = List.of
                (
                        new BookDto(1,
                                "BookTitle_1",
                                new AuthorDto(1, "Author_1"),
                                List.of(new GenreDto(1, "Genre_1"),
                                        new GenreDto(2, "Genre_2")
                                )),
                        new BookDto(2,
                                "BookTitle_2",
                                new AuthorDto(2, "Author_2"),
                                List.of(new GenreDto(3, "Genre_3"),
                                        new GenreDto(4, "Genre_4")
                                )),
                        new BookDto(3,
                                "BookTitle_3",
                                new AuthorDto(3, "Author_3"),
                                List.of(new GenreDto(5, "Genre_5"),
                                        new GenreDto(6, "Genre_6")
                                )));
    }


    @Test
    void findById() {
        assertThat(bookService.findById(BOOK_ID_NOT_EXISTS)).isEmpty();

        var foundBook = bookService.findById(BOOK_ID_EXISTS);

        assertThat(foundBook).isPresent();

        System.out.println("usingRecursiveComparison");
        assertThat(foundBook.get()).usingRecursiveComparison().isEqualTo(expectedBook.get(0));
    }

    @Test
    void findAll() {
        assertThat(bookService.findAll()).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void insert() {
        Book bookExpected = new Book(4,
                "BookTitle_4",
                new Author(3, "Author_3"),
                List.of(new Genre(1, "Genre_1"),
                        new Genre(6, "Genre_6")
                ));
        bookService.insert("BookTitle_4", 3, Set.of(1l, 6l));

        assertThat(bookService.findById(4)).isPresent();

        assertThat(bookService.findById(4).get())
                .usingRecursiveComparison()
                .isEqualTo(bookExpected);

        // вернуть в исходное состояние
        bookService.deleteById(4);
    }

    @Test
    void update() {
        var bookExpected = bookService.findById(2);

        assertThat(bookExpected).isPresent();

        bookExpected.get().setTitle("BookTitle_222");

        bookService.update(bookExpected.get().getId(),
                bookExpected.get().getTitle(),
                bookExpected.get().getAuthor().getId(),
                bookExpected.get().getGenres().stream()
                        .map(x -> x.getId())
                        .collect(Collectors.toSet())
        );

        var bookFound = bookService.findById(bookExpected.get().getId());

        assertThat(bookFound).isPresent();

        assertThat(bookFound.get())
                .usingRecursiveComparison()
                .isEqualTo(bookExpected.get());

        // вернуть в исходное состояние
        bookExpected.get().setTitle("BookTitle_2");
        bookService.update(bookExpected.get().getId(),
                "BookTitle_2",
                bookExpected.get().getAuthor().getId(),
                bookExpected.get().getGenres().stream()
                        .map(x -> x.getId())
                        .collect(Collectors.toSet())
        );
    }

    @Test
    void deleteById() {
        bookService.deleteById(3);

        List<BookDto> books = new ArrayList<>(bookService.findAll());

        Collections.sort(
                books,
                new Comparator<BookDto>() {
                    @Override
                    public int compare(BookDto o1, BookDto o2) {
                        return Long.compare(o1.getId(), o2.getId());
                    }
                }
        );

        assertThat(books)
                .usingRecursiveComparison()
                .isEqualTo(List.of(expectedBook.get(0), expectedBook.get(1)));
    }

    @Test
    void loadContext() {
    }
}