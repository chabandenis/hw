package ru.otus.hw.ex11.services.Game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex11.controller.NotFoundException;
import ru.otus.hw.ex11.dto.GameDto;
import ru.otus.hw.ex11.dto.PositionInChessFairDto;
import ru.otus.hw.ex11.dto.desk.ClmDto;
import ru.otus.hw.ex11.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex11.mapper.GameMapper;
import ru.otus.hw.ex11.mapper.PositionInChessFairMapper;
import ru.otus.hw.ex11.models.ChessFair;
import ru.otus.hw.ex11.models.Figura;
import ru.otus.hw.ex11.models.PositionInChessFair;
import ru.otus.hw.ex11.models.User;
import ru.otus.hw.ex11.repositories.ChessFairRepository;
import ru.otus.hw.ex11.repositories.FiguraRepository;
import ru.otus.hw.ex11.repositories.PositionInChessFairRepository;
import ru.otus.hw.ex11.repositories.UserRepository;
import ru.otus.hw.ex11.repositories.game.GameRepository;
import ru.otus.hw.ex11.services.InputXYService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class GameService {


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



    private PositionInChessFair createChecker(int x, int y, ChessFair chessFair, Figura figura) {
        PositionInChessFair position = new PositionInChessFair(
                null,
                x,
                y,
                chessFair.getId(),
                figura.getId());

        return position;
    }

    public List<PositionInChessFair> fillCheckers(ChessFair chessFair,
                                                  Figura colorWhite,
                                                  Figura colorBlack
    ) {
        List<PositionInChessFair> positions = new ArrayList<>();

        //todo перевернуты XY переделать
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 3; j++) {
                positions.add(createChecker(i, j, chessFair, colorWhite));
            }
        }

        for (int i = 5; i <= 8; i++) {
            for (int j = 6; j <= 8; j++) {
                positions.add(createChecker(i, j, chessFair, colorBlack));
            }
        }

        return positions;
    }

    // пустая доска
    private List<RowOnTheDeskDto> createEmptyDesk() {
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

    // Расставить фигуры на шахматной доске
    public List<RowOnTheDeskDto> fillFigureOnTheDeskDto(List<PositionInChessFairDto> positionsDto) {
        var position = positionsDto.stream()
                .map(x -> PositionInChessFairMapper.toPosition(x))
                .collect(Collectors.toList());

        return fillFigureOnTheDesk(position);
    }

    // Расставить фигуры на шахматной доске.
    public List<RowOnTheDeskDto> fillFigureOnTheDesk(List<PositionInChessFair> positions) {

        var desk = createEmptyDesk();

        for (PositionInChessFair position : positions) {
            String color = "";
            if (position.getFiguraId().equals(MAIN_USER)) {
                color = "O"; // белая шашка
            } else if (position.getFiguraId().equals(SECOND_USER)) {
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
