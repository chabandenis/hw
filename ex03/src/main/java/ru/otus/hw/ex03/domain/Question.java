package ru.otus.hw.ex03.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
