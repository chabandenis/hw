package ru.otus.hw.ex17_game.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex17_game.dto.ChessFairDto;
import ru.otus.hw.ex17_game.models.ChessFair;

@Component
public class ChessFairMapper {
//    ChessFair toEntity(ChessFairDto chessFairDto);

    public static ChessFairDto toChessFairDto(ChessFair chessFair) {
        ChessFairDto chessFairDto = new ChessFairDto();
        chessFairDto.setId(chessFair.getId());
        return chessFairDto;
    }

    ;
}