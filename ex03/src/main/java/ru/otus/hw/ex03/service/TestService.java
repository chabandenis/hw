package ru.otus.hw.ex03.service;

import ru.otus.hw.ex03.domain.Student;
import ru.otus.hw.ex03.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
