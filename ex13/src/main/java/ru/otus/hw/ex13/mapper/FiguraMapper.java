package ru.otus.hw.ex13.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex13.dto.FiguraDto;
import ru.otus.hw.ex13.models.Figura;

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