package ru.otus.hw.ex04.service;

import ru.otus.hw.ex04.domain.Student;
import ru.otus.hw.ex04.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
