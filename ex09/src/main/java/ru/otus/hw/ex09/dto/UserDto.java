package ru.otus.hw.ex09.dto;

import lombok.Data;

/**
 * DTO for {@link ru.otus.hw.ex09.models.User}
 */
@Data
public class UserDto {

    private Long id;

    private String name;
}