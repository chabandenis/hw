package ru.otus.hw.ex05.repositories;

import ru.otus.hw.ex05.models.BookGenre;

import java.util.List;

public interface BookGenreRepository {
    List<BookGenre> findAllByBookId(Long id);
}
