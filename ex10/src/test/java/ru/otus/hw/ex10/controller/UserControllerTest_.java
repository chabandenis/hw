package ru.otus.hw.ex10.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.ex10.dto.fromWeb.WelcomeDto;
import ru.otus.hw.ex10.logic.Cache;
import ru.otus.hw.ex10.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test class for the {@link UserController}
 */
@WebMvcTest({
        UserController.class,
        Cache.class
})
public class UserControllerTest_ {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print())
        ;
    }

    @Test
    public void getWelcome() throws Exception {
        WelcomeDto welcomeExpected = new WelcomeDto();
        welcomeExpected.setName("Иванов Иван Иванович");

//        when(userService.getWelcome("user1"))
//                .thenReturn(
//                        welcomeExpected
//                );

        mockMvc.perform(get("/welcome")
                        .param("login", "user1"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("welcome", welcomeExpected))
                .andDo(print())
        ;
    }
}
