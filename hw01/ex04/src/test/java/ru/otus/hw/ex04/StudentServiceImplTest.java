package ru.otus.hw.ex04;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw.ex04.service.LocalizedIOServiceImpl;
import ru.otus.hw.ex04.service.StudentService;
import ru.otus.hw.ex04.service.TestRunnerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService resultService;

    @MockBean
    LocalizedIOServiceImpl ioService;

    //@MockBean
    //исключил автозапуск
    //private TestRunnerService testRunnerService;

    @Test
    void verifyService() {

        given(ioService.readStringWithPromptLocalized(anyString())).willReturn("XXX");

        assertThat(resultService.determineCurrentStudent().getFullName()).isEqualTo("XXX XXX");
    }

}