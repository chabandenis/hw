package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            var isAnswerValid = false; // Задать вопрос, получить ответ
            System.out.println("Вопрос: " + question.text());

            for (var answer : question.answers())
            {
                System.out.println("\t" + answer.text());
            }

            ioService.readIntForRangeWithPrompt(1, 3, "число:", "Выберите число в указанном диапазоне");

            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}
