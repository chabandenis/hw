package ru.otus.hw.ex11.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
//@Entity
@Table(name = "games")
//@NamedEntityGraph(name = "game-graph",
//        attributeNodes = {
//                @NamedAttributeNode("userBlack"),
//                @NamedAttributeNode("userWhite"),
//                @NamedAttributeNode("userNext"),
//                @NamedAttributeNode("chessFair"),
//        })
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Game {

    // идентификатор игры
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    // пользователь играющий за черных
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_black_id")
    private User userBlack;

    // пользователь играющий за белых
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_white_id")
    private User userWhite;

    //  Пользователь за которым следующий ход
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_next_id")
    private User userNext;

    // шахматная доска с расстановкой фигур
//    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "chess_fair_id")
    private ChessFair chessFair;

    //    @Column(name = "date_game")
    private LocalDateTime dateGame;

}