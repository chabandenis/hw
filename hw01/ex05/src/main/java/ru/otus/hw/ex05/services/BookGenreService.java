package ru.otus.hw.ex05.services;

import ru.otus.hw.ex05.models.BookGenre;

import java.util.List;

public interface BookGenreService {
    List<BookGenre> findAllByBookId(Long id);
}

