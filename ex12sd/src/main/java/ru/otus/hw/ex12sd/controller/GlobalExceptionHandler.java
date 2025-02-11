package ru.otus.hw.ex12sd.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorDto> handleNotFoundException(NotFoundException ex) {
        CustomErrorDto errorDto =
                new CustomErrorDto(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomErrorDto(
                        "Ошибка 404: Ресурс не найден: " + System.lineSeparator() +
                                ex.getMessage() + System.lineSeparator()
                        // + getStackTrace(ex) // очень много текста
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Логирование стек трейса
        //ex.printStackTrace();

        String errorMessage = "Произошла внутренняя ошибка сервера 500: ";
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        errorMessage + ex.getMessage() + System.lineSeparator()
                        // + getStackTrace(ex)
                );
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class CustomErrorDto {
        private String errorMessage;
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
