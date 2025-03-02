package ru.otus.hw.ex15.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

//    @OneToMany(mappedBy = "chessFair", orphanRemoval = true)
//    private List<PositionInChessFair> positionInChessFairs = new ArrayList<>();

}