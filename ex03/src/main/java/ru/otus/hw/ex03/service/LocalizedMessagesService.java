package ru.otus.hw.ex03.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object... args);
}
