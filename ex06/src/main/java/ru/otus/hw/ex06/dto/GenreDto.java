package ru.otus.hw.ex06.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex06.models.Genre}
 */
@Value
public class GenreDto {
    private long id;

    private String name;

/*    @Override
    public boolean equals(Object o) {
        System.out.println("Genre DTO");
        if (o == null || getClass() != o.getClass()) return false;

        GenreDto genreDto = (GenreDto) o;
        return id == genreDto.id;
    }

    @Override
    public int hashCode() {
        System.out.println("Genre DTO hash");
        return Long.hashCode(id);
    }*/
}