package ru.otus.hw.ex12_r_hw.logic;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//@Scope("singleton")
public class Cache {

    private String login;
}
