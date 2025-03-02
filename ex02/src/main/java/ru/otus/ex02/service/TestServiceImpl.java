package ru.otus.ex02.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.ex02.dao.QuestionDao;
import ru.otus.ex02.domain.Student;
import ru.otus.ex02.domain.TestResult;

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

        for (var question : questions) {
            var isAnswerValid = false; // Задать вопрос, получить ответ
            System.out.println("Вопрос: " + question.text());

            for (var answer : question.answers()) {
                System.out.println("\t" + answer.text());
            }

            int enteredAnswer = -1;
            enteredAnswer = ioService.readIntForRangeWithPrompt(1,
                    question.answers().size(),
                    "число:",
                    "Выберите число в указанном диапазоне");
            isAnswerValid = question.answers().get(enteredAnswer - 1).isCorrect();

            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}
