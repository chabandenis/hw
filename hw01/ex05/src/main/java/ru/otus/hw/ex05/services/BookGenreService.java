package ru.otus.hw.ex05.services;

import ru.otus.hw.ex05.models.BookGenre;
import ru.otus.hw.ex05.models.Genre;

import java.util.List;
import java.util.Set;

public interface BookGenreService {
    List<BookGenre> findAllByBookId(Long id);
}

