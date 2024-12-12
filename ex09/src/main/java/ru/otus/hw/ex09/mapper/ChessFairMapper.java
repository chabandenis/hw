package ru.otus.hw.ex09.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.otus.hw.ex09.dto.ChessFairDto;
import ru.otus.hw.ex09.models.ChessFair;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChessFairMapper {
    ChessFair toEntity(ChessFairDto chessFairDto);

    ChessFairDto toChessFairDto(ChessFair chessFair);
}