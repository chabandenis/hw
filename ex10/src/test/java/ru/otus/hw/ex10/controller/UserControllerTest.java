package ru.otus.hw.ex10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.ex10.dto.user.UserResultDto;
import ru.otus.hw.ex10.dto.user.UserLoginDto;
import ru.otus.hw.ex10.dto.user.UserUpdateDto;
import ru.otus.hw.ex10.mapper.UserMapper;
import ru.otus.hw.ex10.models.User;
import ru.otus.hw.ex10.services.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link UserController}
 */
@WebMvcTest({UserController.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void getAll() throws Exception {

        List<UserResultDto> users = List.of(
                new UserResultDto(1L, "userX", "userX" )
                , new UserResultDto(2L, "userY", "userY" )
        );

        given(userService.getAll()).willReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(users)))
                .andDo(print());
    }

    @Test
    public void login() throws Exception {
        UserResultDto user = new UserResultDto(1L, "userX", "userX");
        UserResultDto userIncorrenct = new UserResultDto(2L, "userXX", "userXX");

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setLogin("user1");
        userLoginDto.setPassword("1");

        given(userService.findByLogin(any())).willReturn(userIncorrenct);
        given(userService.findByLogin(userLoginDto)).willReturn(user);

        String userLoginActionDtoStr = """
                {
                    "login": "user1",
                    "password": "1"
                }""";

        mockMvc.perform(post("/api/users/login")
                        .content(userLoginActionDtoStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(user)))
                .andDo(print());
    }

    @Test
    public void create() throws Exception {

        User userParametr = new User();
        userParametr.setId(1l);
        userParametr.setName("userX");
        userParametr.setLogin("userX");
        userParametr.setPassword("2");

        UserResultDto user = UserMapper.toUserAllDto(userParametr);

        String userIn = """
                    {
                        "id": 1,
                        "name": "userX",
                        "login": "userX",
                        "password": "2"
                    }
                """;

        Mockito.when(userService.create(any())).thenReturn(user);

        String expectedString = mapper.writeValueAsString(user);
        System.out.println(expectedString);

        mockMvc.perform(post("/api/users/insert")
                        .content(userIn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedString))
                .andDo(print())
        ;
    }

    @Test
    public void put() throws Exception {
        UserResultDto user = new UserResultDto(1L, "userX", "userX");
        UserUpdateDto userParametrGiven = new UserUpdateDto();
        userParametrGiven.setName("userX");
        userParametrGiven.setLogin("userX");
        //userParametrGiven.setPassword("1");

        String userIn = """
                {
                    "id": 1,
                    "name": "userX",
                    "login": "userX",
                    "password": "1"
                }""";

        //given(userService.update(any())).willReturn(user);
        given(userService.put(1l, userParametrGiven)).willReturn(user);

        mockMvc.perform(post("put")
                        .content(userIn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(user)))
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        UserResultDto user = new UserResultDto(1L, "userX", "userX");

        given(userService.delete(any())).willReturn(user);

        mockMvc.perform(post("/api/users/delete/{0}", "0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(user)))
                .andDo(print());
    }

    @Test
    public void test() throws Exception {

    }
}
