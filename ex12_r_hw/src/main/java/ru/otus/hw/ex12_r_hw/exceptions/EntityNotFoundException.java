package ru.otus.hw.ex12_r_hw.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
