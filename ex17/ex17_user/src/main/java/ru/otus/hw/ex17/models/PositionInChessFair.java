package ru.otus.hw.ex17.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
//@NamedEntityGraph(name = "position-in-chess-fair-graph",
//        attributeNodes = {
//                @NamedAttributeNode("chessFair"),
//                @NamedAttributeNode("figura")
//        })
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class PositionInChessFair {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("id")
    private Long id;

    // позиция по горизонтали
    @Column("position_x")
    private Integer positionX;

    // позиция по вертикали
    @Column("position_y")
    private Integer positionY;

    // доска на которой стоит фишка
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "chess_fair_id")
    private Long chessFairId;

    // пока цвет черный/белый
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "figura_id")
    private Long figuraId;

}