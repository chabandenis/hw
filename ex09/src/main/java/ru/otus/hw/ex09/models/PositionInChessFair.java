package ru.otus.hw.ex09.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "position_in_chess_fair")
public class PositionInChessFair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "position_x")
    private Integer positionX;


    @Column(name = "position_y")
    private Integer positionY;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chess_fair_id")
    private ChessFair chessFair;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "figura_id")
    private Figura figura;

}