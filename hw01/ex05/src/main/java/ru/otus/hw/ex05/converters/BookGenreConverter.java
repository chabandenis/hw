package ru.otus.hw.ex05.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex05.models.BookGenre;

@Component
public class BookGenreConverter {
    public String bookGenreToString(BookGenre bookGenre) {
        return "book_id: %d, genre_id: %s".formatted(bookGenre.getBookId(), bookGenre.getGenreId());
    }
}
