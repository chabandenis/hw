package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }

    private File getFile() {
        String fileName = fileNameProvider.getTestFileName();
        System.out.println("\ngetResource : " + fileName);
        File file = null;
        try {
            file = getFileFromResource(fileName);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    @Override
    public List<Question> findAll() {

        List<QuestionDto> questionsDtos;

        try {
            questionsDtos = new CsvToBeanBuilder(new FileReader(getFile()))
                    .withSeparator(';').withType(QuestionDto.class).build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Question> questions = new ArrayList<>();

        for (QuestionDto questionDtoIndex : questionsDtos) {
            questions.add(questionDtoIndex.toDomainObject());
        }

        return questions;
    }
}
