import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;

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