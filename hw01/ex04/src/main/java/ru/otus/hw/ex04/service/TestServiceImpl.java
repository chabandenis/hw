package ru.otus.hw.ex04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex04.dao.QuestionDao;
import ru.otus.hw.ex04.domain.Question;
import ru.otus.hw.ex04.domain.Student;
import ru.otus.hw.ex04.domain.TestResult;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final LocalizedMessagesServiceImpl localizedMessagesService;

    private final QuestionDao questionDao;

    public void showAnswers(Question question) {
        int i = 0;
        for (var answer : question.answers()) {
            System.out.println("\t" + ++i + " - " + answer.text());
        }
    }

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLineLocalized("TestService.answer.the.questions");
        ioService.printLine("");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question : questions) {
            var isAnswerValid = false; // Задать вопрос, получить ответ
            System.out.println(question.text());
            showAnswers(question);
            int enteredAnswer = -1;
            enteredAnswer = ioService.readIntForRangeWithPrompt(1,
                    question.answers().size(),
                    localizedMessagesService.getMessage("TestService.number"),
                    localizedMessagesService.getMessage("TestService.number.in.range"));

            isAnswerValid = question.answers().get(enteredAnswer - 1).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

}
