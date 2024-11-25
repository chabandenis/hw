package ru.otus.ex02.service;

import ru.otus.ex02.domain.Student;
import ru.otus.ex02.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
