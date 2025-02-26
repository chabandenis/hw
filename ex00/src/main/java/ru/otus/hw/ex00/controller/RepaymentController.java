package ru.otus.hw.ex00.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.ex00.dto.repayment.RepaymentCreateDto;
import ru.otus.hw.ex00.dto.repayment.RepaymentDto;
import ru.otus.hw.ex00.services.RepaymentService;

@Slf4j
@RestController
@RequestMapping("/api/repayment")
@AllArgsConstructor
public class RepaymentController {

    private final RepaymentService repaymentService;

    // создать запрос за погашение кредитного договора
    // http://localhost:8080/api/user
    // {"name":"user5", "login":"login", "password":"1" }
    @PostMapping(value = "")
    public ResponseEntity<RepaymentDto> create(@RequestBody @Valid RepaymentCreateDto repaymentCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repaymentService.create(repaymentCreateDto));
    }


/*
    private final UserService userService;

    // пользователи
    // http://localhost:8080/api/user
    @GetMapping("")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    // авторизация пользователя
    // http://localhost:8080/api/user/login
    // {"login": "user1", "password": "1"}
    @PutMapping(value = "/login")
    public UserDto login(@RequestBody @Valid UserLoginDto userLoginDto) throws Exception {
        return userService.findByLogin(userLoginDto);
    }


    // обновить пользователя
    // http://localhost:8080/api/user/1
    // {"name":"Первый Иван Иваныч Иванов", "login":"login", "password":"1"}
    @PutMapping(value = "/{userId}")
    public UserDto put(@PathVariable Long userId,
                       @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return userService.put(userId, userUpdateDto);

    }

    // удалить пользователя
    // http://localhost:8080/api/user/4
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }*/
}