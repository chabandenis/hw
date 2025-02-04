package ru.otus.hw.ex11.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.dto.user.UserCreateDto;
import ru.otus.hw.ex11.dto.user.UserLoginDto;
import ru.otus.hw.ex11.mapper.UserMapper;
import ru.otus.hw.ex11.repositories.UserRepository;
import ru.otus.hw.ex11.services.Game.GameService;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

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
    };
}

    /*
    public UserDto put(Long userId, UserUpdateDto userUpdateDto) {
        var userUpdated = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Отсутствует пользователь с идентификатором id="
                                + userId
                        )
                );

        userUpdated.setName(userUpdateDto.getName());
        userUpdated.setLogin(userUpdateDto.getLogin());
        userUpdated.setPassword(userUpdateDto.getPassword());

        return UserMapper.toUserDto(userRepository.save(userUpdated));
    }

    public UserDto delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            gameService.deleteByUser(id);
            userRepository.delete(user);
        }
        return UserMapper.toUserDto(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(x -> UserMapper.toUserDto(x))
                .collect(Collectors.toList());
    }

*/

