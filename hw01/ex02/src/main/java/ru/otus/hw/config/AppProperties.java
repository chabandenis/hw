package ru.otus.hw.config;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class AppProperties implements TestConfig, TestFileNameProvider {

    // внедрить свойство из application.properties
    private int rightAnswersCountToPass;

    // внедрить свойство из application.properties
    private String testFileName;
}
