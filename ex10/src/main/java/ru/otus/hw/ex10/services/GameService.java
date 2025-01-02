package ru.otus.hw.ex10.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex10.controller.NotFoundException;
import ru.otus.hw.ex10.dto.GameDto;
import ru.otus.hw.ex10.dto.InputXYDTO;
import ru.otus.hw.ex10.dto.desk.ClmDto;
import ru.otus.hw.ex10.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex10.mapper.GameMapper;
import ru.otus.hw.ex10.models.ChessFair;
import ru.otus.hw.ex10.models.Figura;
import ru.otus.hw.ex10.models.Game;
import ru.otus.hw.ex10.models.PositionInChessFair;
import ru.otus.hw.ex10.repositories.ChessFairRepository;
import ru.otus.hw.ex10.repositories.FiguraRepository;
import ru.otus.hw.ex10.repositories.GameRepository;
import ru.otus.hw.ex10.repositories.PositionInChessFairRepository;
import ru.otus.hw.ex10.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameMapper gameMapper;

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

    private void convert(InputXYDTO inputXYDTO) {
        // координты в DTO
        y1 = 8 - Integer.parseInt(inputXYDTO.getY1());
        x1 = inputXYDTO.getX1().toUpperCase().charAt(0) - 'A';
        // координаты в БД
        y2 = Integer.parseInt(inputXYDTO.getY2());
        x2 = inputXYDTO.getX2().toUpperCase().charAt(0) - 'A' + 1;
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
    public Game newGame() {
        Game game = new Game();
        game.setId(0l);
        ChessFair chessFair = new ChessFair();
        game.setChessFair(chessFair);
        game.setUserWhite(
                userRepository.findById(1l)
                        .orElseThrow(() -> new NotFoundException("Отсутствует пользователь с Id = 1")));
        game.setUserBlack(
                userRepository.findById(2l)
                        .orElseThrow(() -> new NotFoundException("Отсутствует пользователь с Id = 2")));
        game.setUserNext(
                userRepository.findById(1l)
                        .orElseThrow(() -> new NotFoundException("Отсутствует пользователь с Id = 1")));

        chessFair = chessFairRepository.save(chessFair);

        fillCheckers(chessFair);

        game = gameRepository.save(game);

        return game;
    }

    @Transactional
    public GameDto doStep2(
            InputXYDTO inputXYDTO) {
        // проверка значений
        inputXYService.verfif(inputXYDTO);

        GameDto gameDto = getOne(inputXYDTO.getGameId());

        // поиск значений
        convert(inputXYDTO);

        var posId = gameDto.getChessFair().getDesk().get(y1)
                .getArr().get(x1).getPositionId();

        PositionInChessFair position =
                positionInChessFairRepository.findById(posId).get();

        position.setPositionX(x2);
        position.setPositionY(y2);

        positionInChessFairRepository.save(position);

        gameDto = getOne(inputXYDTO.getGameId());

        return gameDto;
    }

    @Transactional
    public void doStep(
            GameDto gameDto,
            InputXYDTO inputXYDTO) {
        // проверка значений
        inputXYService.verfif(inputXYDTO);

        // поиск значений
        convert(inputXYDTO);

        var posId = gameDto.getChessFair().getDesk().get(y1)
                .getArr().get(x1).getPositionId();

        PositionInChessFair position =
                positionInChessFairRepository.findById(posId).get();

        position.setPositionX(x2);
        position.setPositionY(y2);

        positionInChessFairRepository.save(position);

 /*       inputXYDTO.setXFirst("");
        inputXYDTO.setYFirst("");
        inputXYDTO.setXSecond("");
        inputXYDTO.setYSecond("");
*/
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
        var gameDto = gameMapper.toGameDto(
                gameOptional.orElseThrow(
                        () -> new NotFoundException("Entity Game with id=`%s` not found".formatted(id))));

        var chessFair = positionInChessFairRepository.findByChessFairId(gameDto.getChessFair().getId());

        // расставить фигуры в виде удобном для отображения в TL
        gameDto.getChessFair().setDesk(fillFigureOnTheDesk(chessFair));

        return gameDto;
    }

    @Transactional
    public Game delete(Long id) {
        Game game = gameRepository.findById(id).orElse(null);

        positionInChessFairRepository.deleteByChessFair(game.getChessFair());

        if (game != null) {
            gameRepository.delete(game);
        }
        return game;
    }

    @Transactional
    public void deleteByUser(Long id) {
        var games = gameRepository.findByUserBlackIdOrUserWhiteIdOrderByIdDesc(id);
        games.forEach(game -> delete(game.getId()));
        return;
    }


}
