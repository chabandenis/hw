package ru.otus.hw.ex11.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    Один экземпляр класса соответствует одной фишке
    у фишки есть позиция и доска на которой она стоит

 */

@Getter
@Setter
@Entity
@Table(name = "position_in_chess_fairs")
@NamedEntityGraph(name = "position-in-chess-fair-graph",
        attributeNodes = {
                @NamedAttributeNode("chessFair"),
                @NamedAttributeNode("figura")
        })
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PositionInChessFair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // позиция по горизонтали
    @Column(name = "position_x")
    private Integer positionX;

    // позиция по вертикали
    @Column(name = "position_y")
    private Integer positionY;

    // доска на которой стоит фишка
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chess_fair_id")
    private ChessFair chessFair;

    // пока цвет черный/белый
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "figura_id")
    private Figura figura;

}