package ru.otus.hw.ex06.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex06.dto.GenreDto;
import ru.otus.hw.ex06.models.Genre;

@Component
public class GenreConverter {

    public GenreDto toDto(Genre genre) {
        GenreDto genreDto = new GenreDto(
                genre.getId(),
                genre.getName());
        return genreDto;
    }

    public String genreToString(GenreDto genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
