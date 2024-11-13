package ru.otus.hw.ex03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.ex03.config.AppProperties;
import ru.otus.hw.ex03.config.TestFileNameProvider;
import ru.otus.hw.ex03.dao.CsvQuestionDao;
import ru.otus.hw.ex03.dao.QuestionDao;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CsvQuestionDaoTest {
    @Autowired
    private TestFileNameProvider fileNameProvider;

    @DisplayName("Интеграционный тест класса, читающего вопросы")
    @Test
    void verifyReadQuestionFromFile() {
        QuestionDao questionDao = new CsvQuestionDao(fileNameProvider);
        var questions = questionDao.findAll();
        assertThat(questions.size()).isEqualTo(5);
    }

}