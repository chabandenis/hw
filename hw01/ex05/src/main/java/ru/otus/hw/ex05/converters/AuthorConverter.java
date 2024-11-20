package ru.otus.hw.ex05.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex05.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        if (author == null) {
            return "";
        }
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
