package ru.otus.hw.ex04.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object... args);
}
