package ru.otus.hw.ex17_db.models;


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
    private Long id;

    private String name;

}