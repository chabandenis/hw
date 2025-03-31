package ru.otus.hw.ex17_game.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/*
    фигура может быть черной или белой.
 */
@Getter
@Setter
@Table(name = "figuras")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class Figura {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

}