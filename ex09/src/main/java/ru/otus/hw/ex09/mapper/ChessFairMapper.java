package ru.otus.hw.ex09.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex09.dto.ChessFairDto;
import ru.otus.hw.ex09.models.ChessFair;

@Component
public class ChessFairMapper {
//    ChessFair toEntity(ChessFairDto chessFairDto);

    public ChessFairDto toChessFairDto(ChessFair chessFair) {
        ChessFairDto chessFairDto = new ChessFairDto();
        chessFairDto.setId(chessFair.getId());
        return chessFairDto;
    }

    ;
}