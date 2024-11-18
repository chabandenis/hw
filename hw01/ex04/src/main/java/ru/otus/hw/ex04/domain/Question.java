package ru.otus.hw.ex04.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
