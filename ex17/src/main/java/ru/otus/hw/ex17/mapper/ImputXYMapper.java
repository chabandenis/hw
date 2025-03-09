package ru.otus.hw.ex17.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ImputXYMapper {
    Integer xToDto(Integer x) {
        return x - 1;
    }

    Integer yToDto(Integer y) {
        return 8 - y;
    }

    Integer xDtoToX(Integer x) {
        return x + 1;
    }

    Integer yDtoToY(Integer y) {
        return y - 8;
    }

}
