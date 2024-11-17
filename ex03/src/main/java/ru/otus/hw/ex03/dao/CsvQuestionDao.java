package ru.otus.hw.ex03.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex03.config.TestFileNameProvider;
import ru.otus.hw.ex03.dao.dto.QuestionDto;
import ru.otus.hw.ex03.domain.Question;
import ru.otus.hw.ex03.exceptions.QuestionReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new QuestionReadException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    @Override
    public List<Question> findAll() {

        List<QuestionDto> questionsDtos3;
        List<Question> questions = new ArrayList<>();

        try (InputStream inputStream = getFileFromResourceAsStream(fileNameProvider.getTestFileName())) {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            questionsDtos3 = new CsvToBeanBuilder(reader).withSeparator(';').withType(QuestionDto.class).build().parse();

            Objects.requireNonNull(questionsDtos3);
            for (QuestionDto questionDtoIndex : questionsDtos3) {
                questions.add(questionDtoIndex.toDomainObject());
            }
        } catch (IOException e) {
            System.out.println("error: " + fileNameProvider.getTestFileName());
            throw new QuestionReadException(fileNameProvider.getTestFileName());
        }

        return questions;
    }
}
