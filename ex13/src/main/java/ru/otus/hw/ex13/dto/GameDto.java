package ru.otus.hw.ex12_r_hw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.hw.ex12_r_hw.models.Game;

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