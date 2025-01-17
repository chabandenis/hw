package ru.otus.hw.ex11.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.dto.fromWeb.UserLoginActionDto;
import ru.otus.hw.ex11.models.User;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({
        UserService.class,
        GameService.class,
        InputXYService.class
})
class UserServiceTest {

    @Autowired
    private UserService userService;

    private UserDto userDtoExpected;

    @BeforeEach
    void setUp() {
        userDtoExpected = new UserDto(1l, "Первый Иван Иваныч", "user1", "1");
    }

    @Test
    void findByLogin() {
        UserLoginActionDto loginActionDtoExpected = new UserLoginActionDto();
        loginActionDtoExpected.setLogin("user1");
        loginActionDtoExpected.setPassword("1");

        var user = userService.findByLogin(loginActionDtoExpected);
        assertThat(user)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(userDtoExpected);
    }

    @Test
    void insert() {
        User userNew = new User();
        userNew.setName("YYY");
        userNew.setId(null);
        userNew.setLogin("XXX");
        userNew.setPassword("1");

        var userCreated = userService.insert(userNew);

        userNew.setId(userCreated.getId());

        assertThat(userCreated)
                .usingRecursiveComparison()
                .isEqualTo(userNew);

        // вернуть
        userService.delete(userCreated.getId());
    }

    @Test
    void update() {
        UserLoginActionDto loginActionDtoExpected = new UserLoginActionDto();
        loginActionDtoExpected.setLogin("user1");
        loginActionDtoExpected.setPassword("1");

        var userInDb = userService.findByLogin(loginActionDtoExpected);

        User user = new User();
        user.setId(userInDb.getId());
        user.setPassword(userInDb.getPassword());
        user.setLogin(userInDb.getLogin());
        user.setName(userInDb.getName() + " 555");

        var updatedUser = userService.update(user);

        assertThat(updatedUser).usingRecursiveComparison().isEqualTo(user);

        //вернуть
        user.setName(userInDb.getName());
        userService.update(user);
    }

    @Test
    void delete() {
        User userNew = new User();
        userNew.setName("YYY");
        userNew.setId(null);
        userNew.setLogin("XXX");
        userNew.setPassword("1");

        var userCreated = userService.insert(userNew);
        userNew.setId(userCreated.getId());

        var countAfterInsert = userService.getAll().size();
        userService.delete(userCreated.getId());
        var countAfterdelete = userService.getAll().size();

        assertThat(countAfterInsert).isEqualTo(countAfterdelete + 1);
    }

    @Test
    void getAll() {
        assertThat(userService.getAll().size()).isEqualTo(3);
    }

    @Test
    void setup() {
    }
}