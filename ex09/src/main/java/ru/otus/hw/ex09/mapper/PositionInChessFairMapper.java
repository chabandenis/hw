package ru.otus.hw.ex09.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.otus.hw.ex09.dto.PositionInChessFairDto;
import ru.otus.hw.ex09.models.PositionInChessFair;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING, uses = {FiguraMapper.class})
public interface PositionInChessFairMapper {
    PositionInChessFair toEntity(PositionInChessFairDto positionInChessFairDto);

    PositionInChessFairDto toPositionInChessFairDto(PositionInChessFair positionInChessFair);
}