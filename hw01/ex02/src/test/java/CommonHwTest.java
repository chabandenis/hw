import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.Assert;
import ru.otus.hw.Application;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.ResultService;
import ru.otus.hw.service.ResultServiceImpl;
import ru.otus.hw.service.StudentService;
import ru.otus.hw.service.StudentServiceImpl;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class CommonHwTest {

    private static final String CONFIGURATION_ANNOTATION_NAME = "org.springframework.context.annotation.Configuration";
    private static final int rightAnswersCountToPass = 3;
    private static final String testFileName = "questions.csv";

    @DisplayName("Интеграционный тест класса, читающего вопросы")
    @Test
    void verifyReadQuestionFromFile(){
        TestFileNameProvider fileNameProvider = new AppProperties(rightAnswersCountToPass, testFileName);
        QuestionDao questionDao = new CsvQuestionDao(fileNameProvider);
        var questions = questionDao.findAll();
        assertThat(questions.size()).isEqualTo(5);
    }

    @Test
    void verifyService(){
        TestConfig testConfig = new AppProperties(rightAnswersCountToPass, testFileName);;

        IOService ioService;
        ioService = mock(IOService.class);

        given(ioService.readStringWithPrompt(anyString())).willReturn("XXX");
        StudentService resultService = new StudentServiceImpl(ioService);
        assertThat(resultService.determineCurrentStudent().getFullName()).isEqualTo("XXX XXX");
    }

    @DisplayName("")
    @Test
    void shouldNotContainConfigurationAnnotationAboveItSelf() {
        assertThat(AppProperties.class.isAnnotationPresent(Configuration.class))
                .withFailMessage("Класс свойств не является конфигурацией т.к. " +
                        "конфигурация для создания бинов, а тут просто компонент группирующий свойства приложения")
                .isFalse();
    }

    @Test
    void shouldNotContainPropertySourceAnnotationAboveItSelf() {
        assertThat(AppProperties.class.isAnnotationPresent(PropertySource.class))
                .withFailMessage("Аннотацию @PropertySource лучше вешать над конфигурацией, " +
                        "а класс свойств ей не является")
                .isFalse();
    }

    @Test
    void shouldNotContainFieldInjectedDependenciesOrProperties() {
        var provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter((mr, mf) -> {
            var metaData = mr.getClassMetadata();
            var annotationMetaData = mr.getAnnotationMetadata();
            var isTest = metaData.getClassName().endsWith("Test");
            var isInterface = metaData.isInterface();
            var isConfiguration = annotationMetaData.hasAnnotation(CONFIGURATION_ANNOTATION_NAME);
            var clazz = getBeanClassByName(metaData.getClassName());
            var classContainsFieldInjectedDependenciesOrProperties = Arrays.stream(clazz.getDeclaredFields())
                    .anyMatch(f -> f.isAnnotationPresent(Autowired.class) || f.isAnnotationPresent(Value.class));
            return !isTest && !isInterface && !isConfiguration && classContainsFieldInjectedDependenciesOrProperties;
        });

        var classesContainsFieldInjectedDependenciesOrProperties =
                provider.findCandidateComponents(Application.class.getPackageName());

        var classesNames = classesContainsFieldInjectedDependenciesOrProperties.stream()
                .map(BeanDefinition::getBeanClassName).collect(Collectors.joining("%n"));
        assertThat(classesContainsFieldInjectedDependenciesOrProperties)
                .withFailMessage("На курсе все внедрение рекомендовано осуществлять через конструктор (" +
                        "в т.ч. @Value). Следующие классы нарушают это правило: %n%s".formatted(classesNames))
                .isEmpty();
    }

    private Class<?> getBeanClassByName(String beanClassName) {
        try {
            return Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}