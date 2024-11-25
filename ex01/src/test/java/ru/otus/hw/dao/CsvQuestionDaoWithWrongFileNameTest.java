package ru.otus.hw.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Corret file name of question.csv")
class CsvQuestionDaoWithWrongFileNameTest {
    private ApplicationContext context;
    private QuestionDao questionDao;

    private List<Question> questions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext("/spring-context-wrong-file.xml");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        int waitedSize = 0;
        questionDao = context.getBean(QuestionDao.class);
        questions = questionDao.findAll();
        assertThat(waitedSize).isEqualTo(questions.size());
    }
}