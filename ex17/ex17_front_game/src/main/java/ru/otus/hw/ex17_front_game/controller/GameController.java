package ru.otus.hw.ex17_front_game.controller;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_front_game.dto.CoordinatesDto;
import ru.otus.hw.ex17_front_game.dto.GameDto;
import ru.otus.hw.ex17_front_game.dto.GamesCreateDto;
import ru.otus.hw.ex17_front_game.metrics.MetricsManager;

import java.net.URI;
import java.util.List;

import static ru.otus.hw.ex17_front_game.filter.MdcFilter.MDC_AUTHORIZATION;
import static ru.otus.hw.ex17_front_game.filter.MdcFilter.MDC_REQUEST_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    private final RequestCreate requestCreate;
    private final RequestDelete requestDelete;
    private final RequestGamesForUsers requestGamesForUsers;
    private final RequestGetOne requestGetOne;
    private final RequestStep requestStep;

    private final MetricsManager metricsManager;

    private final EurekaClient discoveryClient;

    //private final CheckedFunction<RequestForData, String> getAdditionalInfoFunction;

    // выбрать совместные игры
    // http://localhost:8080/api/game/1/2
    @GetMapping("/{mainUser}/{secondUser}")
    public Flux<GameDto> getGamesForUsers(@PathVariable Long mainUser, @PathVariable Long secondUser) {
        //return gameRepositoryCustom.findAll(mainUser, secondUser);
        return getGamesForUsersInfo(mainUser, secondUser);
    }

    // выполнить ход
    // http://localhost:8080/api/game/1
    // {"x1":"D","y1":1,"x2":"F","y2":1}
    @PutMapping(value = "/{gameId}")
    public Mono<ResponseEntity<GameDto>> step(@PathVariable Long gameId,
                                              @RequestBody CoordinatesDto coordinatesDto) {
        return stepInfo(gameId, coordinatesDto);
        //return gameServiceStep.step(gameId, coordinatesDto);
    }

    // создать игру
    // http://localhost:8080/api/game
    // {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "")
    public Mono<ResponseEntity<GameDto>> create(@RequestBody GamesCreateDto gamesCreateDto) {
        return createInfo(gamesCreateDto);
        //return gameServiceCreate.create(gamesCreateDto);
    }

    // удалить игру
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // http://localhost:8080/api/game/7
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteInfo(id);
        //return gameService.delete(id);
    }

    // информация об игре по заданному идентификатору
    // http://localhost:8080/api/game/1
    @GetMapping("/{id}")
    public Mono<ResponseEntity<GameDto>> getOne(@PathVariable Long id) {
        return getOneInfo(id);
//        return gameServiceGetOne.getOne(id);
    }

    private Flux<GameDto> getGamesForUsersInfo(Long mainUser, Long secondUser) {

        try {
            var clientInfo = discoveryClient.getNextServerFromEureka("GAME", false);
            log.info("GAME from Eureka:{}", clientInfo);
            Flux<GameDto> gamesForUsersInfo = requestGamesForUsers.getGamesForUsers(
                    MDC.get(MDC_REQUEST_ID),
                    MDC.get(MDC_AUTHORIZATION),
                    new URI(clientInfo.getHomePageUrl()),
                    mainUser,
                    secondUser);
            log.info("requestGamesForUsers:{}", gamesForUsersInfo);
            //return gamesForUsersInfo.data();
            return gamesForUsersInfo;
        } catch (Exception ex) {
            log.error("can't get requestGamesForUsers, mainUser:{}, secondUser:{}, error:{}", mainUser, secondUser, ex.getMessage());
            return null;
        }
    }

    //    public Mono<ResponseEntity<GameDto>> step(@PathVariable Long gameId,
//                                              @RequestBody CoordinatesDto coordinatesDto)
    private Mono<ResponseEntity<GameDto>> stepInfo(Long gameId,
                                                   CoordinatesDto coordinatesDto) {
        try {
            var clientInfo = discoveryClient.getNextServerFromEureka("GAME", false);
            log.info("GAME from Eureka:{}", clientInfo);
            var additionalInfo = requestStep.step(
                    MDC.get(MDC_REQUEST_ID),
                    MDC.get(MDC_AUTHORIZATION),
                    new URI(clientInfo.getHomePageUrl()),
                    gameId, coordinatesDto);
            log.info("requestStep:{}", additionalInfo);
            return additionalInfo;
        } catch (Exception ex) {
            log.error("can't get requestStep, name:{}, gameId:{}, coordinatesDto:{}", gameId, coordinatesDto, ex.getMessage());
            return null;
        }
    }

    private Mono<ResponseEntity<GameDto>> createInfo(GamesCreateDto gamesCreateDto) {
        try {
            var clientInfo = discoveryClient.getNextServerFromEureka("GAME", false);
            log.info("GAME from Eureka:{}", clientInfo);
            var additionalInfo = requestCreate.create(
                    MDC.get(MDC_REQUEST_ID),
                    MDC.get(MDC_AUTHORIZATION),
                    new URI(clientInfo.getHomePageUrl()),
                    gamesCreateDto);
            log.info("requestCreate:{}", additionalInfo);
            return additionalInfo;
        } catch (Exception ex) {
            log.error("can't get requestCreate, name:{}, error:{}", gamesCreateDto, ex.getMessage());
            return null;
        }
    }

    private Mono<Void> deleteInfo(Long id) {
        try {
            var clientInfo = discoveryClient.getNextServerFromEureka("GAME", false);
            log.info("GAME from Eureka:{}", clientInfo);
            var additionalInfo = requestDelete.delete(
                    MDC.get(MDC_REQUEST_ID),
                    MDC.get(MDC_AUTHORIZATION),
                    new URI(clientInfo.getHomePageUrl()),
                    id);
            log.info("requestDelete:{}", additionalInfo);
            return additionalInfo;
        } catch (Exception ex) {
            log.error("can't get requestDelete, id:{}, error:{}", id, ex.getMessage());
            return null;
        }
    }

    private Mono<ResponseEntity<GameDto>> getOneInfo(@PathVariable Long id) {
        try {
            var clientInfo = discoveryClient.getNextServerFromEureka("GAME", false);
            log.info("GAME from Eureka:{}", clientInfo);
            var additionalInfo = requestGetOne.getOne(
                    MDC.get(MDC_REQUEST_ID),
                    MDC.get(MDC_AUTHORIZATION),
                    new URI(clientInfo.getHomePageUrl()),
                    id);
            log.info("getOneInfo:{}", additionalInfo);
            return additionalInfo;
        } catch (Exception ex) {
            log.error("can't get getOneInfo, id:{}, error:{}", id, ex.getMessage());
            return null;
        }
    }
}