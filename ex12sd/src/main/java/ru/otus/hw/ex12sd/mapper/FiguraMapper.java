package ru.otus.hw.ex12sd.mapper;

import org.springframework.stereotype.Component;
import ru.otus.hw.ex12sd.dto.FiguraDto;
import ru.otus.hw.ex12sd.models.Figura;

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