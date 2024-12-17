package ru.otus.hw.ex09.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.models.Game;

@Component
@AllArgsConstructor
public class GameMapper {

    private final UserMapper userMapper;

    private ChessFairMapper chessFairMapper;

//    Game toEntity(GameDto gameDto){
//        return null;
//    }

    public GameDto toGameDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setUserWhite(userMapper.toUserDto(game.getUserWhite()));
        gameDto.setId(game.getId());
        gameDto.setUserNext(userMapper.toUserDto(game.getUserNext()));
        gameDto.setChessFair(chessFairMapper.toChessFairDto(game.getChessFair()));
        gameDto.setUserBlack(userMapper.toUserDto(game.getUserBlack()));

        return gameDto;
    }


}