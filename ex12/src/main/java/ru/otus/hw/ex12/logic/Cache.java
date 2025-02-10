package ru.otus.hw.ex12.logic;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//@Scope("singleton")
public class Cache {

    private String login;
}
