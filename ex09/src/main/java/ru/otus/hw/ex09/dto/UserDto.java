package ru.otus.hw.ex09.dto;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex09.models.User}
 */
@Value
public class UserDto {
    Long id;
    String name;
}