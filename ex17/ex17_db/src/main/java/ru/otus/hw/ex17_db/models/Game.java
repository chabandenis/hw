package ru.otus.hw.ex17_db.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/*
Идентификатор игры.

У игры есть:
    игроки(пользователи)
    доска с расстановкой фигур
    игрок который ходит
 */
@Getter
@Setter
@Table(name = "games")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class Game {

    // идентификатор игры
    @Id
    private Long id;

    // пользователь играющий за черных
    private Long userBlackId;

    // пользователь играющий за белых
    private Long userWhiteId;

    //  Пользователь за которым следующий ход
    private Long userNextId;

    // шахматная доска с расстановкой фигур
    private Long chessFairId;

    private LocalDateTime dateGame;
}