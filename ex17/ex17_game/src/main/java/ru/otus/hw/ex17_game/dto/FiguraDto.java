package ru.otus.hw.ex17_game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.ex17_game.models.Figura;

/**
 * DTO for {@link Figura}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiguraDto {

    private Long id;

    private String name;
}