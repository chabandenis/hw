package ru.otus.hw.ex06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for {@link ru.otus.hw.ex06.models.Book}
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