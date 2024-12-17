package ru.otus.hw.ex09.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex09.dto.PositionInChessFairDto;
import ru.otus.hw.ex09.models.PositionInChessFair;

@Component
@AllArgsConstructor
public class PositionInChessFairMapper {

    private final ChessFairMapper chessFairMapper;

    private final FiguraMapper figuraMapper;

//    PositionInChessFair toEntity(PositionInChessFairDto positionInChessFairDto);

    public PositionInChessFairDto toPositionInChessFairDto(PositionInChessFair positionInChessFair) {
        PositionInChessFairDto positionInChessFairDto = new PositionInChessFairDto();
        positionInChessFairDto.setChessFair(
                chessFairMapper.toChessFairDto(positionInChessFair.getChessFair()));
        positionInChessFairDto.setPositionX(positionInChessFair.getPositionX());
        positionInChessFairDto.setPositionY(positionInChessFair.getPositionY());
        positionInChessFairDto.setId(positionInChessFair.getId());
        positionInChessFairDto.setFigura(figuraMapper.toFiguraDto(positionInChessFair.getFigura()));
        return positionInChessFairDto;
    }

    ;
}