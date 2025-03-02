package ru.otus.hw.ex07.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex07.dto.BookDto;
import ru.otus.hw.ex07.models.Book;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;


    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setAuthor(authorConverter.toDto(book.getAuthor()));
        bookDto.setTitle(book.getTitle());

        bookDto.setGenres(
                book.getGenres().stream()
                        .map(genreConverter::toDto)
                        .toList());

        return bookDto;
    }

    public String bookToString(BookDto book) {
        var genresString = book.getGenres().stream()
                .map(genreConverter::genreToString)
                .map("{%s}"::formatted)
                .collect(Collectors.joining(", "));

        return "Id: %d, title: %s, author: {%s}, genres: [%s] ".formatted(
                book.getId(),
                book.getTitle(),
                authorConverter.authorDtoToString(book.getAuthor()),
                genresString);
    }
}
