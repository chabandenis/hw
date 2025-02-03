package ru.otus.hw.ex11.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex11.dto.ChessFairDto;
import ru.otus.hw.ex11.dto.GameDto;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.dto.game.CoordinatesDto;
import ru.otus.hw.ex11.dto.game.GamesCreateDto;
import ru.otus.hw.ex11.repositories.game.GameRepositoryCustom;
import ru.otus.hw.ex11.services.Game.GameServiceCreate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameServiceCreate gameServiceCreate;

    private final GameRepositoryCustom gameRepositoryCustom;

    // выбрать совместные игры
    // http://localhost:8080/api/game/1/2
    @GetMapping("/{mainUser}/{secondUser}")
    public Flux<GameDto> getGamesForUsers(@PathVariable Long mainUser, @PathVariable Long secondUser) {
        return gameRepositoryCustom.findAll(mainUser, secondUser);
    }

    // выполнить ход
    // http://localhost:8080/api/game/1
    // {"x1":"D","y1":1,"x2":"F","y2":1}
    @PutMapping(value = "/{gameId}")
    public Mono<GameDto> step(@PathVariable Long gameId, @RequestBody @Valid CoordinatesDto coordinatesDto) {
        return null;
//        return gameService.step(gameId, coordinatesDto);
    }

    private GameDto addMainUser(GameDto gameDto, UserDto mainUser) {
        gameDto.setUserWhite(mainUser);
        gameDto.setUserNext(mainUser);
        return gameDto;
    }

    private GameDto addSecondUser(GameDto gameDto, UserDto secondUser) {
        gameDto.setUserBlack(secondUser);
        return gameDto;
    }

    private GameDto addChessFair(GameDto gameDto, ChessFairDto chessFair) {
        gameDto.setChessFair(chessFair);
        return gameDto;
    }


    // создать игру
    // http://localhost:8080/api/game
    // {"mainUser": 1,"secondUser": 2}
    @PostMapping(value = "")
    public Mono<ResponseEntity<GameDto>> create(@RequestBody @Valid GamesCreateDto gamesCreateDto) {
        return gameServiceCreate.create(gamesCreateDto);
    }
}




/*
                                return gameRepository.save(new Game()).publishOn(workerPool)
                                        .map(game->GameMapper.toGameDto(game)).flux().last()
                                        .map(ResponseEntity::ok)
                                        .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));

*/


/*
                        return gameRepository.save(GameMapper.toGameDto(new GameDto())).publishOn(workerPool)
                                .map(ResponseEntity::ok)
                                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));

*/



/*

*/
/*
                    var positionsDto = gameService.fillCheckers(chessFairForSaved, figuraWhite, figuraBlack);
                    var positions = positionsDto.stream()
                            .map(PositionInChessFairMapper::toPosition)
                            .collect(Collectors.toList());

                    GameDto gameDto = new GameDto();
                    gameDto.setUserWhite(UserMapper.toUserDto(findMainUser));
                    gameDto.setUserBlack(userMapper.toUserDto(findSecondUser));
                    gameDto.setUserNext(userMapper.toUserDto(findMainUser));
                    gameDto.setChessFair(
                            new ChessFairDto(
                                    chessFair.getId(),
                                    gameService.fillFigureOnTheDesk(positionsDto)
                            ));

                    Game game = GameMapper.toGameDto(gameDto);
*/
/*
                    return positionInChessFairRepository.saveAll(positions)
                            .publishOn(workerPool)
                            .flatMap(positionInChessFair -> {
                                log.debug("07 {}", Thread.currentThread().getId());
                                log.debug("positionInChessFair:{}", positionInChessFair);
                                 //return Mono.just(positionInChessFair);


*/
/*
                    positionInChessFairRepository.saveAll(positions)
                            .publishOn(workerPool)
                            .subscribe(positionInChessFair -> {
                                log.debug("07 {}", Thread.currentThread().getId());
                                log.debug("positionInChessFair:{}", positionInChessFair);
                            });

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return gameRepository.save(game).publishOn(workerPool)
                            .map(savedGame -> {
                                log.debug("44 {}", Thread.currentThread().getId());
                                log.debug("55 {} {}", positions, savedGame.getId());
                                gameDto.setId(savedGame.getId());
                                return gameDto;
                            })
                            .map(ResponseEntity::ok)
                            .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
*/
/*
            });
        });
    });
});
}

*/



/*

// создать игру
// http://localhost:8080/api/game
// {"mainUser": 1,"secondUser": 2}
@PostMapping(value = "")
public Mono<ResponseEntity<GameDto>> create2(@RequestBody @Valid GamesCreateDto gamesCreateDto) {
log.debug("01 {}", Thread.currentThread().getId());
return userRepository.findById(gamesCreateDto.getMainUser()).publishOn(workerPool).flatMap(findMainUser -> {
    log.debug("02 {}", Thread.currentThread().getId());
    log.debug("findMainUser:{}", findMainUser);
    if (findMainUser == null) {
        //return Mono.error(new NotFoundException("Отсутствует пользователь с Id = " + gamesCreateDto.getMainUser()));
    }
    return userRepository.findById(gamesCreateDto.getSecondUser()).publishOn(workerPool).flatMap(findSecondUser -> {
        log.debug("03 {}", Thread.currentThread().getId());
        log.debug("findSecondUser:{}", findSecondUser);
        if (findSecondUser == null) {
            //return Mono.error(new NotFoundException(
            "Отсутствует пользователь с Id = " + gamesCreateDto.getSecondUser()));
        }

        log.debug("034 {}", Thread.currentThread().getId());
        ChessFair chessFairForSaved = new ChessFair();
        //chessFairForSaved.setId(22l);

        return chessFairRepository.save(chessFairForSaved).publishOn(workerPool).flatMap(chessFair -> {
            log.debug("04 {}", Thread.currentThread().getId());
            log.debug("chessFair1:{}", chessFair);
            log.debug("034 {}", chessFairForSaved);


            return figuraRepository.findById(1l).publishOn(workerPool).flatMap(figuraWhite -> {
                log.debug("05 {}", figuraWhite);
                log.debug("figuraWhite:{}", figuraWhite);
                if (figuraWhite == null) {
                    //return Mono.error(new NotFoundException("Ошибка сохранения получении figura c id = 1"));
                }

                return figuraRepository.findById(2l).publishOn(workerPool)
                        .flatMap(figuraBlack -> {
                            log.debug("06 {}", figuraBlack);
                            log.debug("figuraWhite:{}", figuraBlack);
                            if (figuraBlack == null) {
                             //return Mono.error(new NotFoundException("Ошибка сохранения получении figura c id = 2"));
                            }

                            var positionsDto = gameService.fillCheckers(chessFairForSaved, figuraWhite, figuraBlack);
                            var positions = positionsDto.stream()
                                    .map(PositionInChessFairMapper::toPosition)
                                    .collect(Collectors.toList());

                            GameDto gameDto = new GameDto();
                            gameDto.setUserWhite(UserMapper.toUserDto(findMainUser));
                            gameDto.setUserBlack(userMapper.toUserDto(findSecondUser));
                            gameDto.setUserNext(userMapper.toUserDto(findMainUser));


                            Game game = GameMapper.toGameDto(gameDto);

                            return positionInChessFairRepository.saveAll(positions);
                        })
                        */
/*.map(PositionInChessFair::getNoteText)*//*

                        .collectList()
                        .map(pos -> toDto(game, notes))
                        .map(ResponseEntity::ok)
                        .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
                        ;
*/
/*
                    var positionsDto = gameService.fillCheckers(chessFairForSaved, figuraWhite, figuraBlack);
                    var positions = positionsDto.stream()
                            .map(PositionInChessFairMapper::toPosition)
                            .collect(Collectors.toList());

                    GameDto gameDto = new GameDto();
                    gameDto.setUserWhite(UserMapper.toUserDto(findMainUser));
                    gameDto.setUserBlack(userMapper.toUserDto(findSecondUser));
                    gameDto.setUserNext(userMapper.toUserDto(findMainUser));
                    gameDto.setChessFair(
                            new ChessFairDto(
                                    chessFair.getId(),
                                    gameService.fillFigureOnTheDesk(positionsDto)
                            ));

                    Game game = GameMapper.toGameDto(gameDto);
*//*

*/
/*
                    return positionInChessFairRepository.saveAll(positions)
                            .publishOn(workerPool)
                            .flatMap(positionInChessFair -> {
                                log.debug("07 {}", Thread.currentThread().getId());
                                log.debug("positionInChessFair:{}", positionInChessFair);
                                 //return Mono.just(positionInChessFair);


*//*

*/
/*
                    positionInChessFairRepository.saveAll(positions)
                            .publishOn(workerPool)
                            .subscribe(positionInChessFair -> {
                                log.debug("07 {}", Thread.currentThread().getId());
                                log.debug("positionInChessFair:{}", positionInChessFair);
                            });

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return gameRepository.save(game).publishOn(workerPool)
                            .map(savedGame -> {
                                log.debug("44 {}", Thread.currentThread().getId());
                                log.debug("55 {} {}", positions, savedGame.getId());
                                gameDto.setId(savedGame.getId());
                                return gameDto;
                            })
                            .map(ResponseEntity::ok)
                            .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
*//*


            });
        });
    });
});
}
}

*/


/*

        ChessFair chessFairForSaved = new ChessFair();
        chessFairRepository.save(chessFairForSaved).publishOn(workerPool).flatMap(chessFair -> {
            log.debug("chessFair1:{}", chessFair);

            if (chessFair == null) {
                return Mono.error(new NotFoundException("Ошибка сохранения chessFair = " + chessFair));
            }

            figuraRepository.findById(1l).publishOn(workerPool).flatMap(figuraWhite -> {
                log.debug("figuraWhite:{}", figuraWhite);
                if (figuraWhite == null) {
                    return Mono.error(new NotFoundException("Ошибка сохранения получении figura c id = 1"));
                }
                figuraRepository.findById(2l).publishOn(workerPool).flatMap(figuraBlack -> {
                    log.debug("figuraWhite:{}", figuraBlack);
                    if (figuraBlack == null) {
                        return Mono.error(new NotFoundException("Ошибка сохранения получении figura c id = 2"));
                    }

                    Game game = new Game();
                    game.setId(0l);
                    game.setChessFair(chessFair);
                    game.setUserWhite(findMainUser);

                    game.setUserBlack(findSecondUser);
                    game.setUserNext(findMainUser);
                    game.setDateGame(LocalDateTime.now());

//                                     var positions = gameService.fillCheckers(chessFair, figuraWhite, figuraBlack);

*/
/*
                                positionInChessFairRepository.saveAll(positions)
                                        .publishOn(workerPool)
                                        .subscribe(positionInChessFair -> {
                                            log.debug("positionInChessFair:{}", positionInChessFair);
                                        })
                                ;
*//*


*/
/*                                                                          gameRepository.save(game)
                                      .publishOn(workerPool)
                                      .subscribe(gameAfter ->{
                                          log.debug("game:{}", game);
                                          if (game == null) {
                                              Mono.error(new NotFoundException("Ошибка сохранения game"));
                                          }
                                      });
//                                                                            return GameMapper.toGameDto(game);
                            });

*//*

                    //return GameMapper.toGameDto(game);
                    return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(game));


                });
                return Mono.just(ResponseEntity.badRequest().body(new GameDto()));
            });
            return Mono.just(ResponseEntity.badRequest().body(new GameDto()));
        });
        return Mono.just(ResponseEntity.badRequest().body(new GameDto()));
    });

*/
/*
        ResponseEntity.status(HttpStatus.CREATED).body(
        null//gameService.create(gamesCreateDto)
);
*//*

    return Mono.just(ResponseEntity.badRequest().body(new GameDto()));
});
//return Mono.just(null);
//return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(gameDto));
log.debug("return:{}", "null");
return Mono.just(ResponseEntity.badRequest().body(new GameDto()));
}

;



                    // удалить игру
                    @DeleteMapping("/{id}")
                    @ResponseStatus(HttpStatus.NO_CONTENT)
                    // http://localhost:8080/api/game/7
                    public void delete (@PathVariable Long id){
//        gameService.delete(id);
                    }

                    // информация об игре по заданному идентификатору
                    // http://localhost:8080/api/game/1
                    @GetMapping("/{id}")
                    public Mono<GameDto> getOne (@PathVariable Long id){
//        return gameService.getOne(id);
                        return null;
                    }
                }
                */