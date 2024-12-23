package ru.otus.hw.ex07.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.ex07.models.Book;

import java.util.List;

/**
 * DTO for {@link Book}
 */
//@Value
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BookDto {
    private long id;

    private String title;

    private AuthorDto author;

    private List<GenreDto> genres;

}