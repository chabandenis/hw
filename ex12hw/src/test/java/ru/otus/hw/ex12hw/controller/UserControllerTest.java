package ru.otus.hw.ex12hw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.hw.ex12hw.dto.UserDto;
import ru.otus.hw.ex12hw.dto.user.UserLoginDto;
import ru.otus.hw.ex12hw.dto.user.UserUpdateDto;
import ru.otus.hw.ex12hw.mapper.UserMapper;
import ru.otus.hw.ex12hw.models.User;
import ru.otus.hw.ex12hw.services.UserService;

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
    public void test() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        List<UserDto> users = List.of(
                new UserDto(1L, "userX", "userX", "1")
                , new UserDto(2L, "userY", "userY", "1")
        );

        given(userService.getAll()).willReturn(users);
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(users)))
                .andDo(print());
    }

    @Test
    public void login() throws Exception {
        UserDto user = new UserDto(1L, "userX", "userX", "1");
        UserDto userIncorrenct = new UserDto(2L, "userXX", "userXX", "1");

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

        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/login")
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

        UserDto user = UserMapper.toUserAllDto(userParametr);

        String userCreateDto = """
                {
                        "name": "userX",
                        "login": "userX",
                        "password": "2"
                }""";

        Mockito.when(userService.create(any())).thenReturn(user);

        String expectedString = mapper.writeValueAsString(user);
        System.out.println(expectedString);


        mockMvc.perform(post("/api/user")
                        .content(userCreateDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedString))
                .andDo(print());
    }

    @Test
    public void put() throws Exception {
        UserDto user = new UserDto(1L, "userX", "userX", "1");
        UserUpdateDto userParametrGiven = new UserUpdateDto();
        userParametrGiven.setName("userX");
        userParametrGiven.setLogin("userX");
        userParametrGiven.setPassword("1");

        given(userService.put(1l, userParametrGiven)).willReturn(user);

        String userUpdateDto = """
                {
                    "name": "userX",
                    "login": "userX",
                    "password": "1"
                }""";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{0}", "0")
                        .content(userUpdateDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(content().json(mapper.writeValueAsString(user)))
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        UserDto user = new UserDto(1L, "userX", "userX", "1");

        given(userService.delete(any())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{0}", "0"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


}
