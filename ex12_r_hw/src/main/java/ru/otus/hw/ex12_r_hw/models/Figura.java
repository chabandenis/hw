package ru.otus.hw.ex12_r_hw.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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