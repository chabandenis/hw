package ru.otus.hw.ex09.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
Шахматная доска. Доска одна на игру.
Все фигуры, вся необходимая информация привязана к доске.
Сколько игр было, столько досок будет храниться в БД.
Хранится идентификатор доски для определенной игры.
На доске находится набор фигур
*/
@Getter
@Setter
@Entity
@Table(name = "chess_fairs")
public class ChessFair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
}