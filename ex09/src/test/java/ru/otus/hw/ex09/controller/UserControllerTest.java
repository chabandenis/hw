package ru.otus.hw.ex09.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.ex09.repositories.UserRepository;

/**
 * Test class for the {@link UserController}
 */
@WebMvcTest({UserController.class})
//@Import({UserRepository.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void getAll() throws Exception {
//        mockMvc.perform(get("/allUsers"))
//                .andExpect(status().isOk())
//                .andDo(print());
    }
}
