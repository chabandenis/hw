package ru.otus.hw.ex03.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex03.dao.QuestionDao;
import ru.otus.hw.ex03.domain.Student;
import ru.otus.hw.ex03.domain.TestResult;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;
    private final LocalizedMessagesServiceImpl localizedMessagesService;

    private final QuestionDao questionDao;

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

            int i = 0;
            for (var answer : question.answers()) {
                System.out.println("\t" + ++i + " - " + answer.text());
            }

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
