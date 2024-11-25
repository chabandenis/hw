package ru.otus.hw.ex03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw.ex03.service.LocalizedIOServiceImpl;
import ru.otus.hw.ex03.service.StudentService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService resultService;

    @MockBean
    LocalizedIOServiceImpl ioService;

    @Test
    void verifyService() {

        given(ioService.readStringWithPromptLocalized(anyString())).willReturn("XXX");

        assertThat(resultService.determineCurrentStudent().getFullName()).isEqualTo("XXX XXX");
    }

}