package ru.otus.hw.ex11.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex11.dto.GameDto;
import ru.otus.hw.ex11.models.Game;

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
        gameDto.setId(game.getId());

/*        gameDto.setUserWhite(UserMapper.toUserDto(game.getUserWhite()));

        gameDto.setUserNext(UserMapper.toUserDto(game.getUserNext()));
        gameDto.setChessFair(ChessFairMapper.toChessFairDto(game.getChessFair()));
        gameDto.setUserBlack(UserMapper.toUserDto(game.getUserBlack()));
        gameDto.setDateGame(game.getDateGame());

 */

        return gameDto;
    }


    public static Game toGameDto(GameDto gameDto) {
        Game game = new Game();
        game.setId(gameDto.getId());
        game.setUserWhiteId(gameDto.getUserWhite().getId());
        game.setUserNextId(gameDto.getUserNext().getId());
//        game.setChessFairId(ChessFairMapper.toChessFairDto(game.getChessFair()));
        game.setUserBlackId(gameDto.getUserBlack().getId());
//        game.setDateGame(game.getDateGame());

        return game;
    }


}