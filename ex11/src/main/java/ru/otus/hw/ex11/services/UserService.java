package ru.otus.hw.ex11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
/*

    private final UserRepository userRepository;

    private final GameService gameService;

    @Transactional(readOnly = true)
    public UserDto findByLogin(UserLoginActionDto userLoginActionDto) {
        UserDto user = userRepository.findByLoginAndPassword(
                        userLoginActionDto.getLogin(),
                        userLoginActionDto.getPassword())
                .map(UserMapper::toUserDto)
                .orElseThrow(() ->
                        new NotFoundException("Ошибка авторизации для пользователя " + userLoginActionDto.getLogin()));
        return user;
    }

    @Transactional
    public UserDto insert(User user) {
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Transactional
    public UserDto update(User user) {
        var userUpdated = userRepository.findById(user.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Отсутствует пользователь с идентификатором id="
                                + user.getId()
                        )
                );

        userUpdated.setName(user.getName());
        userUpdated.setLogin(user.getLogin());
        userUpdated.setPassword(user.getPassword());

        return UserMapper.toUserDto(userRepository.save(userUpdated));
    }

    @Transactional
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
}
