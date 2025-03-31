package ru.otus.hw.ex17_db.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/*
    Один экземпляр класса соответствует одной фишке
    у фишки есть позиция и доска на которой она стоит

 */

@Getter
@Setter
//@Entity
@Table(name = "position_in_chess_fairs")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class PositionInChessFair {
    @Id
    @Column("id")
    private Long id;

    // позиция по горизонтали
    @Column("position_x")
    private Integer positionX;

    // позиция по вертикали
    @Column("position_y")
    private Integer positionY;

    // доска на которой стоит фишка
    private Long chessFairId;

    // цвет черный/белый
    private Long figuraId;

}