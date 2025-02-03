package ru.otus.hw.ex11.services.Game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex11.dto.ChessFairDto;
import ru.otus.hw.ex11.dto.GameDto;
import ru.otus.hw.ex11.dto.game.GamesCreateDto;
import ru.otus.hw.ex11.mapper.UserMapper;
import ru.otus.hw.ex11.models.ChessFair;
import ru.otus.hw.ex11.models.Figura;
import ru.otus.hw.ex11.models.Game;
import ru.otus.hw.ex11.models.PositionInChessFair;
import ru.otus.hw.ex11.models.User;
import ru.otus.hw.ex11.repositories.ChessFairRepository;
import ru.otus.hw.ex11.repositories.FiguraRepository;
import ru.otus.hw.ex11.repositories.GameRepository;
import ru.otus.hw.ex11.repositories.PositionInChessFairRepository;
import ru.otus.hw.ex11.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class GameServiceCreate {

    private static final String WHITE = "white";

    private static final String BLACK = "black";

    private static final Long MAIN_USER = 1l;

    private static final Long SECOND_USER = 2l;

    private final UserRepository userRepository;

    private final Scheduler workerPool;

    private final FiguraRepository figuraRepository;

    private final ChessFairRepository chessFairRepository;

    private final PositionInChessFairRepository positionInChessFairRepository;

    private final GameRepository gameRepository;

    private final GameService gameService;

    // информация об игре из БД
    private Map<Long, User> userInGame = new HashMap<>();

    private Map<String, Figura> figuraType = new HashMap<>();

    private ChessFair chessFair;

    private List<PositionInChessFair> positions;

    // 1. Информацию о пользователях
    private Mono<ResponseEntity<GameDto>> loadUsers(GamesCreateDto gamesCreateDto) {
        return userRepository.findByIdIn(List.of(gamesCreateDto.getMainUser(), gamesCreateDto.getSecondUser()))
                .publishOn(workerPool)
                // запомнить пользователей
                .doOnNext(usersInDb -> {
                    userInGame.put(usersInDb.getId(), usersInDb);
                    log.debug("1 user: {}; ", usersInDb);
                })
                .map(usersInDb -> new GameDto()).last()
                .flatMap(x -> loadFigures());
    }

    //  2 информация о шашках
    private Mono<ResponseEntity<GameDto>> loadFigures() {
        return figuraRepository.findByIdIn(List.of(1l, 2l)).publishOn(workerPool)
                // запомнить виды шашек
                .doOnNext(figure -> {
                    log.debug("2 figura: {}; ", figure);
                    if (figure.getId() == 1) {
                        figuraType.put(WHITE, figure);
                    } else {
                        figuraType.put(BLACK, figure);
                    }
                })
                .map(y -> new GameDto()).last()
                .flatMap(figuresInGame -> saveChessFairInDb());
    }

    // 3. сохраняю информацию о шахматной доске
    public Mono<ResponseEntity<GameDto>> saveChessFairInDb() {
        return chessFairRepository.save(new ChessFair()).publishOn(workerPool)
                .doOnNext(chess -> {
                    log.debug("3 chessFair {}", chess);
                    chessFair = chess;
                })
                .flatMap(chessFair -> savePositionInDb());
    }

    // 4. сохраняю информацию о шахматной доске
    public Mono<ResponseEntity<GameDto>> savePositionInDb() {
        // 5 сохранить расположение шашек на доске
        positions = gameService.fillCheckers2(chessFair, figuraType.get(WHITE), figuraType.get(BLACK));

        return positionInChessFairRepository.saveAll(positions).publishOn(workerPool)
                .map(z -> new GameDto()).last()
                .flatMap(chess -> saveGameInDb());
    }

    // заполнить dto
    GameDto retGameDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setDateGame(LocalDateTime.now());
        gameDto.setUserWhite(UserMapper.toUserDto(userInGame.get(MAIN_USER)));
        gameDto.setUserBlack(UserMapper.toUserDto(userInGame.get(SECOND_USER)));
        gameDto.setUserNext(UserMapper.toUserDto(userInGame.get(MAIN_USER)));
        gameDto.setChessFair(new ChessFairDto(chessFair.getId(), gameService.fillFigureOnTheDesk2(positions)));
        return gameDto;
    }

    // 4. сохраняю информацию об игре
    public Mono<ResponseEntity<GameDto>> saveGameInDb() {
        return gameRepository.save(new Game()).publishOn(workerPool)
                .map(savedGame -> retGameDto(savedGame))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    public Mono<ResponseEntity<GameDto>> create(GamesCreateDto gamesCreateDto) {
        log.debug("01; {}", Thread.currentThread().getId());
        return loadUsers(gamesCreateDto);
    }
}