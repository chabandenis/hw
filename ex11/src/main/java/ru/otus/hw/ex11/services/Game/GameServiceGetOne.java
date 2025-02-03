package ru.otus.hw.ex11.services.Game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex11.controller.NotFoundException;
import ru.otus.hw.ex11.dto.ChessFairDto;
import ru.otus.hw.ex11.dto.GameDto;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.mapper.GameMapper;
import ru.otus.hw.ex11.mapper.UserMapper;
import ru.otus.hw.ex11.models.Figura;
import ru.otus.hw.ex11.models.Game;
import ru.otus.hw.ex11.models.User;
import ru.otus.hw.ex11.repositories.ChessFairRepository;
import ru.otus.hw.ex11.repositories.FiguraRepository;
import ru.otus.hw.ex11.repositories.PositionInChessFairRepository;
import ru.otus.hw.ex11.repositories.UserRepository;
import ru.otus.hw.ex11.repositories.game.GameRepository;
import ru.otus.hw.ex11.services.InputXYService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class GameServiceGetOne {

    private final UserRepository userRepository;

    private final Scheduler workerPool;

    private final PositionInChessFairRepository positionInChessFairRepository;

    private final GameRepository gameRepository;

    private final GameService gameService;

    private GameDto gameDto;

    private UserDto getUser(List<User> users, Long userId) {
        return UserMapper.toUserDto(
                users.stream().
                        filter(x -> x.getId() == userId)
                        .findFirst()
                        .orElse(null));
    }

    // 1. Информацию о пользователях
    private Mono<ResponseEntity<GameDto>> loadUsers(Game game) {
        return userRepository.findByIdIn(
                        List.of(game.getUserWhiteId(),
                                game.getUserBlackId(),
                                game.getUserNextId())).publishOn(workerPool)
                .collectList()
                .flatMap(users -> {
                    gameDto.setUserWhite(getUser(users, game.getUserWhiteId()));
                    gameDto.setUserBlack(getUser(users, game.getUserBlackId()));
                    gameDto.setUserNext(getUser(users, game.getUserNextId()));

                    return loadChessFair(game);
                });
    }

    private Mono<ResponseEntity<GameDto>> loadChessFair(Game game) {
        return positionInChessFairRepository.findByChessFairId(game.getChessFairId())
                .collectList()
                .flatMap(chessFair -> {
                    gameDto.setChessFair(
                            new ChessFairDto(game.getChessFairId(),
                                    gameService.fillFigureOnTheDesk(chessFair)));
                    return Mono.just(gameDto);
                })
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    private Mono<ResponseEntity<GameDto>> loadChess(Long id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Entity Game with id=`%s` not found".formatted(id))))
                .flatMap(gameFound -> {
                    gameDto = GameMapper.toGameDto(gameFound);
                    return loadUsers(gameFound);
                })
                ;
    }

    public Mono<ResponseEntity<GameDto>> getOne(Long id) {
        return loadChess(id);
    }
}