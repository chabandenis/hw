package ru.otus.hw.ex09.dto;

import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Component;

/**
 * DTO for {@link ru.otus.hw.ex09.models.User}
 */
@Data
public class UserDto {
    Long id;
    String name;
}