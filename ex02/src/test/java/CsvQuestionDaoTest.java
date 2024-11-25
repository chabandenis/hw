import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.ex02.config.AppProperties;
import ru.otus.ex02.config.TestFileNameProvider;
import ru.otus.ex02.dao.CsvQuestionDao;
import ru.otus.ex02.dao.QuestionDao;

import static org.assertj.core.api.Assertions.assertThat;

class CsvQuestionDaoTest {

    private static final int rightAnswersCountToPass = 3;

    private static final String testFileName = "questions.csv";

    @DisplayName("Интеграционный тест класса, читающего вопросы")
    @Test
    void verifyReadQuestionFromFile() {
        TestFileNameProvider fileNameProvider = new AppProperties(rightAnswersCountToPass, testFileName);
        QuestionDao questionDao = new CsvQuestionDao(fileNameProvider);
        var questions = questionDao.findAll();
        assertThat(questions.size()).isEqualTo(5);
    }

}