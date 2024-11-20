package ru.otus.hw.ex05.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex05.models.BookGenre;
import ru.otus.hw.ex05.repositories.BookGenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookGenreServiceImpl implements BookGenreService {
    private final BookGenreRepository genreRepository;

    @Override
    public List<BookGenre> findAllByBookId(Long id) {
        return genreRepository.findAllByBookId(id);
    }
}
