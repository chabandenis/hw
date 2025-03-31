package ru.otus.hw.ex17_db.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/*
Шахматная доска. Доска одна на игру.
Все фигуры, вся необходимая информация привязана к доске.
Сколько игр было, столько досок будет храниться в БД.
Хранится идентификатор доски для определенной игры.
На доске находится набор фигур
*/
@Getter
@Setter
@Table(name = "chess_fairs")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class ChessFair {

    @Id
    private Long id;
}