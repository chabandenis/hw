package ru.otus.hw.ex00.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
    Информация для погашения кредитного договора
 */
@Getter
@Setter
@Entity
@Table(name = "repayment")
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_cred")
    private String numCred;

    @Column(name = "status")
    private String status;

}