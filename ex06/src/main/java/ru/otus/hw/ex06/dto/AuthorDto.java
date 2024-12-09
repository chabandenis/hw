package ru.otus.hw.ex06.dto;

import lombok.Value;
import ru.otus.hw.ex06.models.Author;

/**
 * DTO for {@link Author}
 */
@Value
public class AuthorDto {
    private long id;

    private String fullName;

/*
    @Override
    public boolean equals(Object o) {
        System.out.println("AuthorDto");
        if (o == null || getClass() != o.getClass()) return false;

        AuthorDto authorDto = (AuthorDto) o;
        return id == authorDto.id;
    }

    @Override
    public int hashCode() {
        System.out.println("AuthorDto hash");
        return Long.hashCode(id);
    }

 */
}