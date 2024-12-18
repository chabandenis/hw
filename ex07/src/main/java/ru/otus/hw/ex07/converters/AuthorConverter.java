package ru.otus.hw.ex07.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex07.dto.AuthorDto;
import ru.otus.hw.ex07.models.Author;

@Component
public class AuthorConverter {
    public AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto(
                author.getId(),
                author.getFullName());
        return authorDto;
    }

    public String authorDtoToString(AuthorDto author) {
        if (author == null) {
            return "";
        }
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
