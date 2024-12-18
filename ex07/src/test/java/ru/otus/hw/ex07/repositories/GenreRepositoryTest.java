package ru.otus.hw.ex07.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw.ex07.models.Genre;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findAllByIds() {
        var foundGenres = genreRepository.findByIdIn(Set.of(1l, 2l));

        List<Genre> expectedGenres = List.of(new Genre(1, "Genre_1"),
                new Genre(2, "Genre_2"));


        assertThat(foundGenres).isEqualTo(expectedGenres);

    }
}