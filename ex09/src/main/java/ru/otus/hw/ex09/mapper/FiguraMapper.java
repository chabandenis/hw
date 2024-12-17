package ru.otus.hw.ex09.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex09.dto.FiguraDto;
import ru.otus.hw.ex09.models.Figura;

@Component
public class FiguraMapper {
    //Figura toEntity(FiguraDto figuraDto);

    public FiguraDto toFiguraDto(Figura figura) {
        FiguraDto figuraDto = new FiguraDto();
        figuraDto.setId(figura.getId());
        figuraDto.setName(figura.getName());
        return figuraDto;
    }
}