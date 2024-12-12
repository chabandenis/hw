package ru.otus.hw.ex09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex09.mapper.GameMapper;
import ru.otus.hw.ex09.mapper.PositionInChessFairMapper;
import ru.otus.hw.ex09.models.Game;
import ru.otus.hw.ex09.models.PositionInChessFair;
import ru.otus.hw.ex09.repositories.GameRepository;
import ru.otus.hw.ex09.repositories.PositionInChessFairRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameMapper gameMapper;

    private final PositionInChessFairMapper positionInChessFairMapper;

    private final GameRepository gameRepository;

    private final PositionInChessFairRepository positionInChessFairRepository;


    // пустая доска
    List<RowOnTheDeskDto> createEmptyDesk() {
        List<RowOnTheDeskDto> desk = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            RowOnTheDeskDto row = new RowOnTheDeskDto();
            row.setA(" ");
            row.setB(" ");
            row.setC(" ");
            row.setD(" ");
            row.setE(" ");
            row.setF(" ");
            row.setG(" ");
            row.setH(" ");

            switch (i) {
                case (0):
                    row.setLeftClm("H");
                    break;
                case (1):
                    row.setLeftClm("G");
                    break;
                case (2):
                    row.setLeftClm("F");
                    break;
                case (3):
                    row.setLeftClm("E");
                    break;
                case (4):
                    row.setLeftClm("D");
                    break;
                case (5):
                    row.setLeftClm("C");
                    break;
                case (6):
                    row.setLeftClm("B");
                    break;
                case (7):
                    row.setLeftClm("A");
                    break;
            }

            desk.add(row);
        }

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

            // супер гениальнй код, исхожу из примера использования шаблонизатора
            switch (position.getPositionX()) {
                case (1):
                    desk.get(position.getPositionY()-1).setA(color);
                    break;
                case (2):
                    desk.get(position.getPositionY()-1).setB(color);
                    break;
                case (3):
                    desk.get(position.getPositionY()-1).setC(color);
                    break;
                case (4):
                    desk.get(position.getPositionY()-1).setD(color);
                    break;
                case (5):
                    desk.get(position.getPositionY()-1).setE(color);
                    break;
                case (6):
                    desk.get(position.getPositionY()-1).setF(color);
                    break;
                case (7):
                    desk.get(position.getPositionY()-1).setG(color);
                    break;
                case (8):
                    desk.get(position.getPositionY()-1).setH(color);
                    break;
            }

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
