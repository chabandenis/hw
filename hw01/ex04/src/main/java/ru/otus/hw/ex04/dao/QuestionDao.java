package ru.otus.hw.ex04.dao;

import ru.otus.hw.ex04.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
