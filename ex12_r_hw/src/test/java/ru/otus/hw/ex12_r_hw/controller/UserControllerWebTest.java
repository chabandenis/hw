package ru.otus.hw.ex12_r_hw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.hw.ex12_r_hw.security.SecurityConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link UserController}
 */
@WebMvcTest({UserController.class})
@Import(SecurityConfiguration.class)
public class UserControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void verif() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void login() throws Exception {
/*        String userLoginDto = """
                {
                    "login": "",
                    "password": ""
                }""";

        mockMvc.perform(put("/api/user/login")
                        .content(userLoginDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());*/
    }

    @Test
    public void create() throws Exception {
        String userCreateDto = """
                {
                    "name": "",
                    "login": "",
                    "password": ""
                }""";

        mockMvc.perform(post("/api/user")
                        .content(userCreateDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void put() throws Exception {
        String userUpdateDto = """
                {
                    "name": "",
                    "login": "",
                    "password": ""
                }""";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{0}", "0")
                        .content(userUpdateDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{0}", "0"))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andDo(print());
    }
}
