import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw.Application;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.StudentService;
import ru.otus.hw.service.StudentServiceImpl;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class StudentServiceImplTest {

    private static final int rightAnswersCountToPass = 3;

    private static final String testFileName = "questions.csv";

    @Test
    void verifyService(){
        TestConfig testConfig = new AppProperties(rightAnswersCountToPass, testFileName);;

        IOService ioService;
        ioService = mock(IOService.class);

        given(ioService.readStringWithPrompt(anyString())).willReturn("XXX");
        StudentService resultService = new StudentServiceImpl(ioService);
        assertThat(resultService.determineCurrentStudent().getFullName()).isEqualTo("XXX XXX");
    }

}