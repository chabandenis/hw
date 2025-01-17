package ru.otus.hw.ex11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
/*
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handeNotFoundException(NotFoundException ex) {
        String errorText = ex.getMessage();
        return new ModelAndView("customError", "errorText", errorText);
    }

 */
}
