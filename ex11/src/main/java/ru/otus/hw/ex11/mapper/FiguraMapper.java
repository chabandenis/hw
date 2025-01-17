package ru.otus.hw.ex11.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex11.dto.FiguraDto;
import ru.otus.hw.ex11.models.Figura;

@Component
public class FiguraMapper {
    //Figura toEntity(FiguraDto figuraDto);

    public static FiguraDto toFiguraDto(Figura figura) {
        FiguraDto figuraDto = new FiguraDto();
        figuraDto.setId(figura.getId());
        figuraDto.setName(figura.getName());
        return figuraDto;
    }
}