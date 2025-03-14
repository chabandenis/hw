package ru.otus.hw.ex09.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.models.Game;

@Component
@AllArgsConstructor
public class GameMapper {

//    private final UserMapper userMapper;

//    private ChessFairMapper chessFairMapper;

//    Game toEntity(GameDto gameDto){
//        return null;
//    }

    public static GameDto toGameDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setUserWhite(UserMapper.toUserDto(game.getUserWhite()));
        gameDto.setId(game.getId());
        gameDto.setUserNext(UserMapper.toUserDto(game.getUserNext()));
        gameDto.setChessFair(ChessFairMapper.toChessFairDto(game.getChessFair()));
        gameDto.setUserBlack(UserMapper.toUserDto(game.getUserBlack()));

        return gameDto;
    }


}