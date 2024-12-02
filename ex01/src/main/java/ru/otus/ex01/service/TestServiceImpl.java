package ru.otus.ex01.service;

import lombok.RequiredArgsConstructor;
import ru.otus.ex01.dao.QuestionDao;
import ru.otus.ex01.domain.Answer;
import ru.otus.ex01.domain.Question;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    private void printQuestion(List<Question> questions) {
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
        List<Question> questions = new ArrayList<>();
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // Получить вопросы из дао и вывести их с вариантами ответов
        questions = questionDao.findAll();
        printQuestion(questions);
    }
}
