package ru.otus.ex02;

import org.junit.jupiter.api.Test;
import ru.otus.ex02.config.AppProperties;
import ru.otus.ex02.config.TestConfig;
import ru.otus.ex02.service.IOService;
import ru.otus.ex02.service.StudentService;
import ru.otus.ex02.service.StudentServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class StudentServiceImplTest {

    private static final int rightAnswersCountToPass = 3;

    private static final String testFileName = "questions.csv";

    @Test
    void verifyService() {
        TestConfig testConfig = new AppProperties(rightAnswersCountToPass, testFileName);
        ;

        IOService ioService;
        ioService = mock(IOService.class);

        given(ioService.readStringWithPrompt(anyString())).willReturn("XXX");
        StudentService resultService = new StudentServiceImpl(ioService);
        assertThat(resultService.determineCurrentStudent().getFullName()).isEqualTo("XXX XXX");
    }

}