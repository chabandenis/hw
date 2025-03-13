package ru.otus.hw.ex16.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@ToString
@Table("сaterpillar")
// гусеница
public class Caterpillar {

    @Id
    private String id;

    private String name;

}
