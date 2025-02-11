package ru.otus.hw.ex12hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex12hw.controller.NotFoundException;
import ru.otus.hw.ex12hw.dto.GameDto;
import ru.otus.hw.ex12hw.dto.desk.ClmDto;
import ru.otus.hw.ex12hw.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex12hw.dto.game.CoordinatesDto;
import ru.otus.hw.ex12hw.dto.game.GamesCreateDto;
import ru.otus.hw.ex12hw.mapper.GameMapper;
import ru.otus.hw.ex12hw.models.ChessFair;
import ru.otus.hw.ex12hw.models.Figura;
import ru.otus.hw.ex12hw.models.Game;
import ru.otus.hw.ex12hw.models.PositionInChessFair;
import ru.otus.hw.ex12hw.repositories.ChessFairRepository;
import ru.otus.hw.ex12hw.repositories.FiguraRepository;
import ru.otus.hw.ex12hw.repositories.GameRepository;
import ru.otus.hw.ex12hw.repositories.PositionInChessFairRepository;
import ru.otus.hw.ex12hw.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {

    //private final GameMapper gameMapper;

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    private final PositionInChessFairRepository positionInChessFairRepository;

    private final InputXYService inputXYService;

    private final ChessFairRepository chessFairRepository;

    private final FiguraRepository figuraRepository;

    private int x1;

    private int y1;

    private int x2;

    private int y2;

    private void convert(CoordinatesDto coordinatesDto) {
        // координты в DTO
        y1 = 8 - Integer.parseInt(coordinatesDto.getY1());
        x1 = coordinatesDto.getX1().toUpperCase().charAt(0) - 'A';
        // координаты в БД
        y2 = Integer.parseInt(coordinatesDto.getY2());
        x2 = coordinatesDto.getX2().toUpperCase().charAt(0) - 'A' + 1;
    }

    void createChecker(int x, int y, ChessFair chessFair, Figura figura) {
        PositionInChessFair position = new PositionInChessFair();
        position.setFigura(figura);
        position.setPositionX(x);
        position.setPositionY(y);
        position.setChessFair(chessFair);

        positionInChessFairRepository.save(position);
    }

    void fillCheckers(ChessFair chessFair) {
        var colorWhite = figuraRepository
                .findById(1l)
                .orElseThrow(() -> new NotFoundException("Отсутствует фигура с id=1"));
        var colorBlack = figuraRepository
                .findById(2l)
                .orElseThrow(() -> new NotFoundException("Отсутствует фигура с id=2"));

        //todo перевернуты XY переделать
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 3; j++) {
                createChecker(i, j, chessFair, colorWhite);
            }
        }

        for (int i = 5; i <= 8; i++) {
            for (int j = 6; j <= 8; j++) {
                createChecker(i, j, chessFair, colorBlack);
            }
        }

    }

    @Transactional
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
    }

    @Transactional
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
    }

    // пустая доска
    @Transactional
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
    @Transactional
    List<RowOnTheDeskDto> fillFigureOnTheDesk(List<PositionInChessFair> positions) {

        var desk = createEmptyDesk();

        for (PositionInChessFair position : positions) {
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

    @Transactional(readOnly = true)
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
    }

    @Transactional
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
    }

    @Transactional
    public void deleteByUser(Long id) {
        var games = gameRepository.findByUserBlackIdOrUserWhiteIdOrderByIdDesc(id);
        games.forEach(game -> delete(game.getId()));
    }

    public List<GameDto> getGamesForUsers(Long mainUserId, Long secondUserId) {
        return gameRepository.findByUserId1AndUserId2OrderByDateGameDesc(mainUserId, secondUserId)
                .stream()
                .map(
                        x -> GameMapper.toGameDto(x))
                .collect(Collectors.toList());
    }
}
