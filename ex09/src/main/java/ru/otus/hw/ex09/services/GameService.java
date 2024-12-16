package ru.otus.hw.ex09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.dto.InputXYDTO;
import ru.otus.hw.ex09.dto.PositionInChessFairDto;
import ru.otus.hw.ex09.dto.desk.ClmDto;
import ru.otus.hw.ex09.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex09.mapper.GameMapper;
import ru.otus.hw.ex09.mapper.PositionInChessFairMapper;
import ru.otus.hw.ex09.models.Game;
import ru.otus.hw.ex09.models.PositionInChessFair;
import ru.otus.hw.ex09.repositories.GameRepository;
import ru.otus.hw.ex09.repositories.PositionInChessFairRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Log4j
@RequiredArgsConstructor
@Service
public class GameService {

    private final GameMapper gameMapper;

    private final PositionInChessFairMapper positionInChessFairMapper;

    private final GameRepository gameRepository;

    private final PositionInChessFairRepository positionInChessFairRepository;

    private final InputXYService inputXYService;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private void convert(InputXYDTO inputXYDTO) {
        y1 = Integer.parseInt(inputXYDTO.getYFirst());
        x1 = inputXYDTO.getXFirst().toUpperCase().charAt(0) - 'A' + 1;
        y2 = Integer.parseInt(inputXYDTO.getYSecond());
        x2 = inputXYDTO.getXSecond().toUpperCase().charAt(0) - 'A' + 1;
    }

    @Transactional
    public void doStep(
            GameDto gameDto,
            InputXYDTO inputXYDTO) {
        // проверка значений
        inputXYService.verfif(inputXYDTO);

        // поиск значений
        convert(inputXYDTO);

/*        Optional<PositionInChessFairDto> pos = gameDto.getChessFair().getPositionInChessFairDtos()
                .stream()
                .filter(x -> x.getPositionX() == x1 && x.getPositionY() == y1)
                .findFirst();

        System.out.println(pos);

 */
    }

    // пустая доска
    List<RowOnTheDeskDto> createEmptyDesk() {
        List<RowOnTheDeskDto> desk = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            RowOnTheDeskDto row = new RowOnTheDeskDto();
            Map<Integer, ClmDto> arr = new HashMap<>();
            for (int j = 0; j < 8; j++) {
                arr.put(j, new ClmDto("", new PositionInChessFairDto()));
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
                    , new PositionInChessFairDto()));
        }
        row.setArr(bottomLine);
        desk.add(row);

        return desk;
    }

    // Расставить фигуры на шахматной доске. Размер фиксирован.
    // При отображении в таблице показываются значения из массива
    List<RowOnTheDeskDto> fillFigureOnTheDesk(List<PositionInChessFair> positions) {

        var desk = createEmptyDesk();

        for (PositionInChessFair position : positions) {
            String color = "";
            if (position.getFigura().getName().contains("Белый")) {
                color = "O"; // белая шашка
            } else if (position.getFigura().getName().contains("Черный")) {
                color = "X"; // черная шашка
            }


            desk.get(8 - position.getPositionY()).getArr().put(position.getPositionX()-1, new ClmDto(color, null));

/*
            switch (position.getPositionX()) {
                case (1):

                    break;
                case (2):
                    desk.get(8 - position.getPositionY()).getArr().put(0, color);
                    break;
                case (3):
                    desk.get(8 - position.getPositionY()).getArr().put(0, color);
                    break;
                case (4):
                    desk.get(8 - position.getPositionY()).getArr().put(0, color);
                    break;
                case (5):
                    desk.get(8 - position.getPositionY()).getArr().put(0, color);
                    break;
                case (6):
                    desk.get(8 - position.getPositionY()).getArr().put(0, color);
                    break;
                case (7):
                    desk.get(8 - position.getPositionY()).getArr().put(0, color);
                    break;
                case (8):
                    desk.get(8 - position.getPositionY()).setH(color);
                    break;
            }
*/
            position.getPositionX();
        }

        return desk;

    }

    public GameDto getOne(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        // отдельно обработать фигуры на доске
        var gameDto = gameMapper.toGameDto(gameOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));

        var chessFair = positionInChessFairRepository.findByChessFair_Id(gameDto.getChessFair().getId());


        /* возможно потребуется сохранение
        gameDto.getChessFair().setPositionInChessFairDtos(
                chessFair
                        .stream()
                        .map(positionInChessFairMapper::toPositionInChessFairDto)
                        .toList()
        );*/

        // расставить фигуры в виде удобном для отображения в TL
        gameDto.getChessFair().setDesk(fillFigureOnTheDesk(chessFair));

        return gameDto;
    }
}
