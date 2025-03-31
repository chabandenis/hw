package ru.otus.hw.ex17_front_game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private Long id;

    private UserDto userBlack;

    private UserDto userWhite;

    private UserDto userNext;

    private ChessFairDto chessFair;

    private LocalDateTime dateGame;
}