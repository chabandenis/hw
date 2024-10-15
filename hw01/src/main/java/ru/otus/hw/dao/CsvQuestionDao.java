package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.AnswerCsvConverter;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;
    private final QuestionDto questionDto;
    private final AnswerCsvConverter answerCsvConverter;

    // print a file
    private void printFile(File file) {

        List<QuestionDto> questionsDtos;

        try {
            questionsDtos = new CsvToBeanBuilder(new FileReader(
                    getFileFromResource(fileNameProvider.getTestFileName())))
                    .withSeparator(';')
                    .withType(QuestionDto.class).build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println("questionsDto " + questionsDtos + "\n");

        List<Question> questions = new ArrayList<>();

        for (QuestionDto questionDtoIndex : questionsDtos) {
            questions.add(questionDtoIndex.toDomainObject());
        }

        for (Question question : questions) {
            System.out.println("Question=" + question.text());
            for (Answer answer : question.answers()) {
                System.out.println("\t" + answer.text());
            }
        }
        System.out.println("questions " + questions + "\n");

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            //lines.forEach(System.out::println);
            for (String str : lines) {
                System.out.println("#" + str);
                // +                questionDto.getAnswers();
            }
            ;

            System.out.println("Разбор 1 ");
            for (String str : lines) {
                System.out.println("$ " + questionDto.getText() + "; " + questionDto.getAnswers());
                ;
            }
            ;

            System.out.println("Разбор 2 ");
            for (String str : lines) {
                System.out.println("$ " + answerCsvConverter.convertToRead(str));
                ;
            }
            ;


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    The resource URL is not working in the JAR
    If we try to access a file that is inside a JAR,
    It throws NoSuchFileException (linux), InvalidPathException (Windows)

    Resource URL Sample: file:java-io.jar!/json/file1.json
 */
    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    @Override
    public List<Question> findAll() {
        // Использовать CsvToBean
        // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
        // Использовать QuestionReadException
        // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/

        //String fileName = "database.properties";
        String fileName = fileNameProvider.getTestFileName();//"json/file1.json";
        System.out.println("\ngetResource : " + fileName);
        File file = null;
        try {
            file = getFileFromResource(fileName);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        printFile(file);

        return new ArrayList<>();
    }
}
