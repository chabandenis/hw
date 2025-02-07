package ru.otus.hw.ex11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.hw.ex11.models.Game;

import java.time.LocalDateTime;

/**
 * DTO for {@link Game}
 */
@Data
@ToString
//@Builder
@NoArgsConstructor
public class GameDto {
    private Long id;

    private UserDto userBlack;

    private UserDto userWhite;

    private UserDto userNext;

    private ChessFairDto chessFair;

    private LocalDateTime dateGame;
}