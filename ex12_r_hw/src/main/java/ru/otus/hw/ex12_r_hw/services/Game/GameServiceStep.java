package ru.otus.hw.ex12_r_hw.services.Game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex12_r_hw.dto.GameDto;
import ru.otus.hw.ex12_r_hw.dto.game.CoordinatesDto;
import ru.otus.hw.ex12_r_hw.models.Figura;
import ru.otus.hw.ex12_r_hw.models.User;
import ru.otus.hw.ex12_r_hw.repositories.PositionInChessFairRepository;
import ru.otus.hw.ex12_r_hw.services.InputXYService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class GameServiceStep {

    private final PositionInChessFairRepository positionInChessFairRepository;

    private final GameServiceGetOne gameServiceGetOne;

    private final InputXYService inputXYService;

    private int x1;

    private int y1;

    private int x2;

    private int y2;

    // информация об игре из БД
    private Map<Long, User> userInGame = new HashMap<>();

    private Map<String, Figura> figuraType = new HashMap<>();

    private void convert(CoordinatesDto coordinatesDto) {
        // координты в DTO
        y1 = 8 - Integer.parseInt(coordinatesDto.getY1());
        x1 = coordinatesDto.getX1().toUpperCase().charAt(0) - 'A';
        // координаты в БД
        y2 = Integer.parseInt(coordinatesDto.getY2());
        x2 = coordinatesDto.getX2().toUpperCase().charAt(0) - 'A' + 1;
    }

    public Mono<ResponseEntity<GameDto>> step(
            Long id,
            CoordinatesDto coordinatesDto) {
        // проверка значений
        inputXYService.verfif(coordinatesDto);
        // поиск значений
        convert(coordinatesDto);
        return gameServiceGetOne.getOne(id).
                flatMap(gameDto -> {
                    var posId = gameDto.getBody().getChessFair().getDesk().get(y1)
                            .getArr().get(x1).getPositionId();

                    return positionInChessFairRepository.findById(posId).flatMap(position -> {
                                position.setPositionX(x2);
                                position.setPositionY(y2);

                                return positionInChessFairRepository.save(position).flatMap(
                                        y -> {
                                            return gameServiceGetOne.getOne(id);
                                        }
                                );
                            }
                    );
                });
    }
}