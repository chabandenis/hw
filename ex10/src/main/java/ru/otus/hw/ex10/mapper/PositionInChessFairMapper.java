package ru.otus.hw.ex10.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex10.dto.PositionInChessFairDto;
import ru.otus.hw.ex10.models.PositionInChessFair;

@Component
@AllArgsConstructor
public class PositionInChessFairMapper {

//    private final ChessFairMapper chessFairMapper;

//    private final FiguraMapper figuraMapper;

//    PositionInChessFair toEntity(PositionInChessFairDto positionInChessFairDto);

    public static PositionInChessFairDto toPositionInChessFairDto(PositionInChessFair positionInChessFair) {
        PositionInChessFairDto positionInChessFairDto = new PositionInChessFairDto();
        positionInChessFairDto.setChessFair(
                ChessFairMapper.toChessFairDto(positionInChessFair.getChessFair()));
        positionInChessFairDto.setPositionX(positionInChessFair.getPositionX());
        positionInChessFairDto.setPositionY(positionInChessFair.getPositionY());
        positionInChessFairDto.setId(positionInChessFair.getId());
        positionInChessFairDto.setFigura(FiguraMapper.toFiguraDto(positionInChessFair.getFigura()));
        return positionInChessFairDto;
    }

    ;
}