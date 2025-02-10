package ru.otus.hw.ex12.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex12.controller.NotFoundException;
import ru.otus.hw.ex12.dto.UserDto;
import ru.otus.hw.ex12.dto.user.UserCreateDto;
import ru.otus.hw.ex12.dto.user.UserLoginDto;
import ru.otus.hw.ex12.dto.user.UserUpdateDto;
import ru.otus.hw.ex12.mapper.UserMapper;
import ru.otus.hw.ex12.repositories.UserRepository;
import ru.otus.hw.ex12.repositories.game.GameRepository;
import ru.otus.hw.ex12.services.Game.GameService;

import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    private final GameService gameService;

    private final Scheduler workerPool;

    public Mono<UserDto> findByLogin(UserLoginDto userLoginDto) {
        return userRepository.findByLoginAndPassword(
                        userLoginDto.getLogin(),
                        userLoginDto.getPassword())
                .publishOn(workerPool)
                .map(UserMapper::toUserDto);
    }

    public Mono<ResponseEntity<UserDto>> create(UserCreateDto userCreateDto) {
        log.debug("Создать пользователя {} ", userCreateDto);
        return userRepository.save(UserMapper.toUser(userCreateDto)).publishOn(workerPool)
                .map(y -> UserMapper.toUserDto(y))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    public Mono<Void> delete(Long id) {
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

    public Mono<UserDto> put(Long userId, UserUpdateDto userUpdateDto) {
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
}


