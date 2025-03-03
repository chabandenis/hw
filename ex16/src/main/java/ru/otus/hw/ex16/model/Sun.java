package ru.otus.hw.ex16.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@ToString
// гусеница
public class Sun {

    @Id
    private String id;

    private String name;

}
