package ru.otus.ex02.dao;

import ru.otus.ex02.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
