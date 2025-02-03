package ru.otus.hw.ex11.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex11.dto.PositionInChessFairDto;
import ru.otus.hw.ex11.models.ChessFair;
import ru.otus.hw.ex11.models.Figura;
import ru.otus.hw.ex11.models.PositionInChessFair;

@Component
@AllArgsConstructor
public class PositionInChessFairMapper {

//    private final ChessFairMapper chessFairMapper;

//    private final FiguraMapper figuraMapper;

//    PositionInChessFair toEntity(PositionInChessFairDto positionInChessFairDto);

    public static PositionInChessFairDto toPositionInChessFairDto(
            ChessFair chessFair,
            Figura white,
            Figura black,
            PositionInChessFair positionInChessFair) {
        PositionInChessFairDto positionInChessFairDto = new PositionInChessFairDto();

        positionInChessFairDto.setChessFair(ChessFairMapper.toChessFairDto(chessFair));
        positionInChessFairDto.setPositionX(positionInChessFair.getPositionX());
        positionInChessFairDto.setPositionY(positionInChessFair.getPositionY());
        positionInChessFairDto.setId(positionInChessFair.getId());

        if (positionInChessFair.getFiguraId().equals(1)) {
            positionInChessFairDto.setFigura(FiguraMapper.toFiguraDto(white));
        } else {
            positionInChessFairDto.setFigura(FiguraMapper.toFiguraDto(black));
        }

        return positionInChessFairDto;
    }

    public static PositionInChessFair toPosition(PositionInChessFairDto positionDto) {
        PositionInChessFair position = new PositionInChessFair();
        position.setId(positionDto.getId());
        position.setPositionX(positionDto.getPositionX());
        position.setPositionY(positionDto.getPositionY());
        position.setChessFairId(positionDto.getChessFair().getId());
        position.setFiguraId(positionDto.getFigura().getId());
        return position;
    }
}