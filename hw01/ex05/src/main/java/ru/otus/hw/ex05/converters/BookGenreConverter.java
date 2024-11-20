package ru.otus.hw.ex05.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex05.models.BookGenre;
import ru.otus.hw.ex05.models.Genre;

@Component
public class BookGenreConverter {
    public String bookGenreToString(BookGenre bookGenre) {
        return "book_id: %d, genre_id: %s".formatted(bookGenre.getBook_id(), bookGenre.getGenre_id());
    }
}
