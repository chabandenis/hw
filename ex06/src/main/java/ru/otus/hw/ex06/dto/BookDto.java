package ru.otus.hw.ex06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        BookDto bookDto = (BookDto) o;
        return id == bookDto.id  ;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        return result;
    }
}