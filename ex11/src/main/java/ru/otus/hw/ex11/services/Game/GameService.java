package ru.otus.hw.ex11.services.Game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex11.dto.PositionInChessFairDto;
import ru.otus.hw.ex11.dto.desk.ClmDto;
import ru.otus.hw.ex11.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex11.mapper.ChessFairMapper;
import ru.otus.hw.ex11.mapper.FiguraMapper;
import ru.otus.hw.ex11.models.ChessFair;
import ru.otus.hw.ex11.models.Figura;
import ru.otus.hw.ex11.models.PositionInChessFair;
import ru.otus.hw.ex11.models.User;
import ru.otus.hw.ex11.repositories.ChessFairRepository;
import ru.otus.hw.ex11.repositories.FiguraRepository;
import ru.otus.hw.ex11.repositories.GameRepository;
import ru.otus.hw.ex11.repositories.PositionInChessFairRepository;
import ru.otus.hw.ex11.repositories.UserRepository;
import ru.otus.hw.ex11.services.InputXYService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class GameService {

    private static final String WITE = "white";

    private static final String BLACK = "black";

    private final UserRepository userRepository;

    private final Scheduler workerPool;

    private final FiguraRepository figuraRepository;

    private final ChessFairRepository chessFairRepository;

    private final PositionInChessFairRepository positionInChessFairRepository;

    private final GameRepository gameRepository;

    //private final GameMapper gameMapper;

//    private final GameRepository gameRepository;

//    private final UserRepository userRepository;

//    private final PositionInChessFairRepository positionInChessFairRepository;

    private final InputXYService inputXYService;

//    private final ChessFairRepository chessFairRepository;

//    private final FiguraRepository figuraRepository;

    // информация об игре из БД
    private Map<Long, User> userInGame = new HashMap<>();

    private Map<String, Figura> figuraType = new HashMap<>();

/*    private void convert(CoordinatesDto coordinatesDto) {
        // координты в DTO
        y1 = 8 - Integer.parseInt(coordinatesDto.getY1());
        x1 = coordinatesDto.getX1().toUpperCase().charAt(0) - 'A';
        // координаты в БД
        y2 = Integer.parseInt(coordinatesDto.getY2());
        x2 = coordinatesDto.getX2().toUpperCase().charAt(0) - 'A' + 1;
    }*/



/*
    // создать игру
// http://localhost:8080/api/game
// {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "\2")
    public Mono<ResponseEntity<GameDto>> create2(@RequestBody @Valid GamesCreateDto gamesCreateDto) {
        log.debug("01; {}", Thread.currentThread().getId());
        return
                // 1. создаю игру -----------------------------------------------
                gameRepository.save(new Game()).publishOn(workerPool)
                .map(game -> GameMapper.toGameDto(game)).flatMap(game -> {
                    log.debug("02; game {}; {}", Thread.currentThread().getId(), game);

                    // 2. получаю информацию о первом пользователе
                    return userRepository.findById(gamesCreateDto.getMainUser()).publishOn(workerPool)
                    .doOnNext(mainUser -> addMainUser(game, UserMapper.toUserDto(mainUser))).flatMap(firstUser -> {
                        log.debug("03; firstUser {}; {}", Thread.currentThread().getId(), firstUser);

                        // 3. получаю информацию о втором пользователе
                        return userRepository.findById(gamesCreateDto.getSecondUser()).publishOn(workerPool)
                        .doOnNext(secondUser -> addSecondUser(game, UserMapper.toUserDto(secondUser)))
                        .flatMap(secondUser -> {
                            log.debug("04; SecondUser {}; {}", Thread.currentThread().getId(), secondUser);

                            // 4. сохраняю информацию о шахматной доске
                            return chessFairRepository.save(new ChessFair()).publishOn(workerPool)
                            .doOnNext(chessFair2 -> addChessFair(game, ChessFairMapper.toChessFairDto(chessFair2)))
                            .flatMap(chessFair -> {
                                log.debug("05; chessFair {}; {}", Thread.currentThread().getId(), chessFair);

                                // 5. белая шашка
                                return figuraRepository.findById(1l).publishOn(workerPool)
                                        //.doOnNext(white -> addFigure(game, FiguraMapper.toFiguraDto(white)))
                                        .flatMap(white -> {
                                            log.debug("06; white {}; {}", Thread.currentThread().getId(), white);

                                            // 6. черная шашка
                                            return figuraRepository.findById(2l).publishOn(workerPool)
                                            .flatMap(black -> {
                                                log.debug("07; black {}; {}", Thread.currentThread().getId(), black);

                                                var positions = gameService.fillCheckers2(chessFair, white, black);

                                                // 7 расставить шашки на доске
                                                return positionInChessFairRepository.saveAll(positions)
                                                .publishOn(workerPool).doOnNext(pos -> log.debug("pos {} ", pos)
                                                        */
    /*game.getChessFair().getDesk().add(pos)*//*
).map(gameUpdate -> game).last()*/
    /*addFigure(game, null)).last()*//*
.flatMap(gameUpdate -> {
        log.debug("08; gameUpdate {}; {}", Thread.currentThread().getId(), gameUpdate);
        log.debug("09; positions {}", positions);

        game.setChessFair(new ChessFairDto(chessFair.getId(), gameService.fillFigureOnTheDesk2(positions)

        ));

        // 8 сохранить обновленную игру
        return gameRepository.save(GameMapper.toGameDto(game)).publishOn(workerPool)
        .map(savedGame -> game).map(ResponseEntity::ok)
        .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    });


});

                                        });
                            });
                        });
                    });
                });
    }
}
*/

    PositionInChessFair createChecker2(int x, int y, ChessFair chessFair, Figura figura) {
        PositionInChessFair position = new PositionInChessFair(
                null,
                x,
                y,
                chessFair.getId(),
                figura.getId());

        return position;

    }


/*    @Transactional
    public GameDto step(
            Long id,
            CoordinatesDto coordinatesDto) {
        // проверка значений
        inputXYService.verfif(coordinatesDto);

        GameDto gameDto = getOne(id);

        // поиск значений
        convert(coordinatesDto);

        var posId = gameDto.getChessFair().getDesk().get(y1)
                .getArr().get(x1).getPositionId();

        PositionInChessFair position =
                positionInChessFairRepository.findById(posId).get();

        position.setPositionX(x2);
        position.setPositionY(y2);

        positionInChessFairRepository.save(position);

        gameDto = getOne(id);

        return gameDto;
    }*/



/*    @Transactional
    public GameDto create(GamesCreateDto gamesCreateDto) {
        Game game = new Game();
        game.setId(0l);
        ChessFair chessFair = new ChessFair();
        game.setChessFair(chessFair);
        game.setUserWhite(
                userRepository.findById(gamesCreateDto.getMainUser())
                        .orElseThrow(() -> new NotFoundException(
                                "Отсутствует пользователь с Id = " + gamesCreateDto.getMainUser())));
        game.setUserBlack(
                userRepository.findById(gamesCreateDto.getSecondUser())
                        .orElseThrow(() -> new NotFoundException(
                                "Отсутствует пользователь с Id = " + gamesCreateDto.getSecondUser())));
        game.setUserNext(
                userRepository.findById(gamesCreateDto.getMainUser())
                        .orElseThrow(() -> new NotFoundException(
                                "Отсутствует пользователь с Id = " + gamesCreateDto.getMainUser())));
        game.setDateGame(LocalDateTime.now());
        chessFair = chessFairRepository.save(chessFair);
        fillCheckers(chessFair);
        game = gameRepository.save(game);
        return GameMapper.toGameDto(game);
    }*/

    public List<PositionInChessFair> fillCheckers2(ChessFair chessFair,
                                                   Figura colorWhite,
                                                   Figura colorBlack
    ) {
        List<PositionInChessFair> positions = new ArrayList<>();

        //todo перевернуты XY переделать
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 3; j++) {
                positions.add(createChecker2(i, j, chessFair, colorWhite));
            }
        }

        for (int i = 5; i <= 8; i++) {
            for (int j = 6; j <= 8; j++) {
                positions.add(createChecker2(i, j, chessFair, colorBlack));
            }
        }

        return positions;
    }

    // пустая доска
    List<RowOnTheDeskDto> createEmptyDesk() {
        List<RowOnTheDeskDto> desk = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            RowOnTheDeskDto row = new RowOnTheDeskDto();
            Map<Integer, ClmDto> arr = new HashMap<>();
            for (int j = 0; j < 8; j++) {
                arr.put(j, new ClmDto("", null));
            }
            row.setLeftClm(String.valueOf(8 - i));
            row.setRightClm(row.getLeftClm());
            row.setArr(arr);
            desk.add(row);
        }
        // нумерация снизу
        RowOnTheDeskDto row = new RowOnTheDeskDto();
        Map<Integer, ClmDto> bottomLine = new HashMap<>();
        for (int j = 0; j < 8; j++) {
            bottomLine.put(j, new ClmDto(String.valueOf((char) ("A".charAt(0) + j))
                    , null));
        }
        row.setArr(bottomLine);
        desk.add(row);
        return desk;
    }

    // Расставить фигуры на шахматной доске. Размер фиксирован.
// При отображении в таблице показываются значения из массива
    public List<RowOnTheDeskDto> fillFigureOnTheDesk(List<PositionInChessFairDto> positions) {

        var desk = createEmptyDesk();

        for (PositionInChessFairDto position : positions) {
            String color = "";
            if (position.getFigura().getName().contains("Белый")) {
                color = "O"; // белая шашка
            } else if (position.getFigura().getName().contains("Черный")) {
                color = "X"; // черная шашка
            }

            desk.get(8 - position.getPositionY())
                    .getArr()
                    .put(position.getPositionX() - 1,
                            new ClmDto(color, position.getId()));

            position.getPositionX();
        }

        return desk;
    }

    // Расставить фигуры на шахматной доске. Размер фиксирован.
// При отображении в таблице показываются значения из массива
    public List<RowOnTheDeskDto> fillFigureOnTheDesk2(List<PositionInChessFair> positions) {

        var desk = createEmptyDesk();

        for (PositionInChessFair position : positions) {
            String color = "";
            if (position.getFiguraId().equals(1)) {
                color = "O"; // белая шашка
            } else if (position.getFiguraId().equals(2)) {
                color = "X"; // черная шашка
            }

            desk.get(8 - position.getPositionY())
                    .getArr()
                    .put(position.getPositionX() - 1,
                            new ClmDto(color, position.getId()));

            position.getPositionX();
        }

        return desk;
    }


// Расставить фигуры на шахматной доске. Размер фиксирован.
// При отображении в таблице показываются значения из массива
/*    public RowOnTheDeskDto fillFigureOnTheDesk2(PositionInChessFairDto positions) {
        RowOnTheDeskDto rowOnTheDeskDto = new RowOnTheDeskDto();

            String color = "";
            if (position.getFigura().getName().contains("Белый")) {
                color = "O"; // белая шашка
            } else if (position.getFigura().getName().contains("Черный")) {
                color = "X"; // черная шашка
            }

            desk.get(8 - position.getPositionY())
                    .getArr()
                    .put(position.getPositionX() - 1,
                            new ClmDto(color, position.getId()));

            position.getPositionX();

        return rowOnTheDeskDto;
    }*/



/*    @Transactional(readOnly = true)
    public GameDto getOne(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        // отдельно обработать фигуры на доске
        var gameDto = GameMapper.toGameDto(
                gameOptional.orElseThrow(
                        () -> new NotFoundException("Entity Game with id=`%s` not found".formatted(id))));

        var chessFair = positionInChessFairRepository.findByChessFairId(gameDto.getChessFair().getId());

        // расставить фигуры в виде удобном для отображения в TL
        gameDto.getChessFair().setDesk(fillFigureOnTheDesk(chessFair));

        return gameDto;
    }*/

/*    @Transactional
    public GameDto delete(Long id) {
        if (id == null) {
            throw new NotFoundException("В запросе на удаление отсутствует id игры");
        }

        Game game = gameRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Отсутствует игра с идентификатором " + id));

        positionInChessFairRepository.deleteByChessFair(game.getChessFair());

        if (game != null) {
            try {
                gameRepository.delete(game);
            } catch (Exception e) {
                throw new NotFoundException("Возникла ошибка при удалении игры с id=" + id);
            }
        }
        return GameMapper.toGameDto(game);
    }*/

/*    @Transactional
    public void deleteByUser(Long id) {
        var games = gameRepository.findByUserBlackIdOrUserWhiteIdOrderByIdDesc(id);
        games.forEach(game -> delete(game.getId()));
    }*/

/*    public List<GameDto> getGamesForUsers(Long mainUserId, Long secondUserId) {
        return gameRepository.findByUserId1AndUserId2OrderByDateGameDesc(mainUserId, secondUserId)
                .stream()
                .map(
                        x -> GameMapper.toGameDto(x))
                .collect(Collectors.toList());
    }*/
}
