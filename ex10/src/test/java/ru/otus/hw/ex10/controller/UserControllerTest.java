package ru.otus.hw.ex10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.ex10.dto.UserDto;
import ru.otus.hw.ex10.dto.fromWeb.UserLoginActionDto;
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

        List<UserDto> users = List.of(
                new UserDto(1L, "userX", "userX", "1")
                , new UserDto(2L, "userY", "userY", "1")
        );

        given(userService.getAll()).willReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(users)))
                .andDo(print());
    }

    @Test
    public void login() throws Exception {
        UserDto user = new UserDto(1L, "userX", "userX", "1");

        UserLoginActionDto userLoginActionDto = new UserLoginActionDto();
        userLoginActionDto.setLogin("user1");
        userLoginActionDto.setPassword("1");

        given(userService.findByLogin(userLoginActionDto)).willReturn(user);

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
    public void insert() throws Exception {

        UserDto user = new UserDto(1L, "userX", "userX", "1");

        String userIn = """
                {
                    "id": 0,
                    "name": "",
                    "login": "",
                    "password": ""
                }""";

        given(userService.insert(any())).willReturn(user);

        mockMvc.perform(post("/api/users/insert")
                        .content(userIn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(user)))
                .andDo(print())
        ;
    }

    @Test
    public void update() throws Exception {
        UserDto user = new UserDto(1L, "userX", "userX", "1");

        String userIn = """
                {
                    "id": 0,
                    "name": "",
                    "login": "",
                    "password": ""
                }""";

        given(userService.update(any())).willReturn(user);

        mockMvc.perform(post("/api/users/update")
                        .content(userIn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(user)))
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        UserDto user = new UserDto(1L, "userX", "userX", "1");

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
