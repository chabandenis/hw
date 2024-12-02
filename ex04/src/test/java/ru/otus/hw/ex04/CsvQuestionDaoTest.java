package ru.otus.hw.ex04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw.ex04.config.TestFileNameProvider;
import ru.otus.hw.ex04.dao.CsvQuestionDao;
import ru.otus.hw.ex04.dao.QuestionDao;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CsvQuestionDaoTest {
    @Autowired
    private TestFileNameProvider fileNameProvider;

    //@MockBean
    //исключил автозапуск
    //private TestRunnerService testRunnerService;

    @DisplayName("Интеграционный тест класса, читающего вопросы")
    @Test
    void verifyReadQuestionFromFile() {
        QuestionDao questionDao = new CsvQuestionDao(fileNameProvider);
        var questions = questionDao.findAll();
        assertThat(questions.size()).isEqualTo(5);
    }

}