package ru.otus.hw.ex07.services;

import ru.otus.hw.ex07.dto.BookDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService {

    Optional<BookDto> findById(long id);

    List<BookDto> findAll();

    BookDto insert(String title, long authorId, Set<Long> genresIds);

    BookDto update(long id, String title, long authorId, Set<Long> genresIds);

    void deleteById(long id);
}
