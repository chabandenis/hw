package ru.otus.hw.ex05.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookGenre {
    private long bookId;

    private long genreId;
}
