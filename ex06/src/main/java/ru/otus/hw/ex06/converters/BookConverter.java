package ru.otus.hw.ex06.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex06.dto.BookDto;
import ru.otus.hw.ex06.models.Book;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    private final CommentConverter commentConverter;


    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();

/*        bookDto.setCommentBooks(
                book.getComment()
                        .stream()
                        .map(commentConverter::toDto)
                        .toList()
        );

 */
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

        var commentsString = book.getCommentBooks().stream()
                .map(commentConverter::commentDtoToString)
                .collect(Collectors.joining(", "));

        return "Id: %d, title: %s, author: {%s}, genres: [%s], comments: [%s]".formatted(
                book.getId(),
                book.getTitle(),
                authorConverter.authorDtoToString(book.getAuthor()),
                genresString,
                commentsString);
    }
}
