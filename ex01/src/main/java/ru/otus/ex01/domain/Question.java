package ru.otus.ex01.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
