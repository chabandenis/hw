package ru.otus.hw.ex05.services;

import ru.otus.hw.ex05.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
