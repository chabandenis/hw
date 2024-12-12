package ru.otus.hw.ex09.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.otus.hw.ex09.dto.FiguraDto;
import ru.otus.hw.ex09.models.Figura;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FiguraMapper {
    Figura toEntity(FiguraDto figuraDto);

    FiguraDto toFiguraDto(Figura figura);
}