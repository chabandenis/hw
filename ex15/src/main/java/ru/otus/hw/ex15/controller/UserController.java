package ru.otus.hw.ex15.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex15.dto.UserDto;
import ru.otus.hw.ex15.dto.user.UserCreateDto;
import ru.otus.hw.ex15.dto.user.UserLoginDto;
import ru.otus.hw.ex15.dto.user.UserUpdateDto;
import ru.otus.hw.ex15.mapper.UserMapper;
import ru.otus.hw.ex15.repositories.UserRepository;
import ru.otus.hw.ex15.repositories.game.GameRepository;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    private final Scheduler workerPool;

    private final GameRepository gameRepository;

    // пользователи
    // http://localhost:8080/api/user
    @GetMapping("")
    public Flux<UserDto> getAll() {
        return userRepository.findAll().publishOn(workerPool).map(user -> UserMapper.toUserDto(user));
    }

    // авторизация пользователя
    // http://localhost:8080/api/user/login
    // {"login": "user1", "password": "1"}
    @PutMapping(value = "/login")
    public Mono<UserDto> login(@RequestBody @Valid UserLoginDto userLoginDto) throws Exception {
        return userRepository.findByLoginAndPassword(
                        userLoginDto.getLogin(),
                        userLoginDto.getPassword())
                .publishOn(workerPool)
                .map(UserMapper::toUserDto);
    }

    // создать пользователя
    // http://localhost:8080/api/user
    // {"name":"user5", "login":"login", "password":"1" }
    @PostMapping(value = "")
    public Mono<ResponseEntity<UserDto>> create(@RequestBody /*@Valid*/ UserCreateDto userCreateDto) {
        log.debug("Создать пользователя {} ", userCreateDto);
        return userRepository.save(UserMapper.toUser(userCreateDto)).publishOn(workerPool)
                .map(y -> UserMapper.toUserDto(y))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));

    }

    // обновить пользователя
    // http://localhost:8080/api/user/1
    // {"name":"Первый Иван Иваныч Иванов", "login":"login", "password":"1"}
    @PutMapping(value = "/{userId}")
    public Mono<UserDto> put(@PathVariable Long userId,
                             @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return userRepository.findById(userId).publishOn(workerPool)
                .switchIfEmpty(Mono.error(new NotFoundException(
                        "Отсутствует пользователь с id=`%s`".formatted(userId))))
                .flatMap(user -> {
                            log.debug("user befor {}", user);
                            user.setName(userUpdateDto.getName());
                            user.setLogin(userUpdateDto.getLogin());
                            user.setPassword(userUpdateDto.getPassword());

                            log.debug("user after {}", user);

                            return userRepository.save(user).publishOn(workerPool)
                                    .map(UserMapper::toUserDto)
                                    .doOnSuccess(x -> log.debug("Обновлен пользователь"));
                        }
                );
    }

    // удалить пользователя
    // http://localhost:8080/api/user/4
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return gameRepository.findByUserWhiteIdInAndUserBlackIdInOrderByDateGameDesc(List.of(id), List.of(id))
                .publishOn(workerPool)
                .collectList()
                .flatMap(games -> {
                    log.debug("Игры удалены");
                    return userRepository.deleteById(id)
                            .publishOn(workerPool)
                            .doOnSuccess(user -> {
                                log.debug("Пользователь удален");
                            });
                });
    }
}