package ru.otus.hw.ex03.dao;

import ru.otus.hw.ex03.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
