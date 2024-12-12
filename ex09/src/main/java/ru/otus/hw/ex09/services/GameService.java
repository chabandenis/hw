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

        for (int i = 1; i <= 8; i++) {
            RowOnTheDeskDto row = new RowOnTheDeskDto();
            row.setA(" ");
            row.setB(" ");
            row.setC(" ");
            row.setD(" ");
            row.setE(" ");
            row.setF(" ");
            row.setG(" ");
            row.setH(" ");

            desk.add(row);
        }

        return desk;
    }

    // Расставить фигуры на шахматной доске. Размер фиксирован.
    // При отображении в таблице показываются значения из массива
    List<RowOnTheDeskDto> fillFigureOnTheDesk(List<PositionInChessFair> positions) {

        var desk = createEmptyDesk();

        for (PositionInChessFair position : positions) {
            if (position.getFigura().getName().contains("Белый")) {
                desk = "X";
            } else if (position.getFigura().getName().contains("Черный")) {
                desk = "O";
            }
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
