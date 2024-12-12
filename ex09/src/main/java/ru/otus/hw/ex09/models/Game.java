package ru.otus.hw.ex09.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
/*
Идентификатор игры.

У игры есть:
    игроки(пользователи)
    доска с расстановкой фигур
    игрок который ходит
 */
@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {

    // идентификатор игры
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // пользователь играющий за черных
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_black_id")
    private User userBlack;

    // пользователь играющий за белых
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_white_id")
    private User userWhite;

    //  Пользователь за которым следующий ход
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_next_id")
    private User userNext;

    // шахматная доска с расстановкой фигур
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "chess_fair_id")
    private ChessFair chessFair;
}