package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    private List<Question> questions = new ArrayList<>();

    private void printQuestion() {
        for (Question question : questions) {
            System.out.println("\nQuestion=" + question.text());
            int i = 1;
            for (Answer answer : question.answers()) {
                answer.setPosition(i);
                System.out.println("\t" + answer.getPosition() + "; " + answer.getText());
                i++;
            }
        }
    }

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // Получить вопросы из дао и вывести их с вариантами ответов
        questions = questionDao.findAll();
        printQuestion();
    }
}
