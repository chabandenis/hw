package ru.otus.ex01.dao;

import ru.otus.ex01.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
