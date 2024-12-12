package ru.otus.hw.ex09.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.otus.hw.ex09.models.Game;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, UserMapper.class, UserMapper.class})
public interface GameMapper {
    Game toEntity(GameDto gameDto);

    GameDto toGameDto(Game game);
}