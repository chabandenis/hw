package ru.otus.hw.ex12_r_hw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw.ex12_r_hw.config.ApplConfig;
import ru.otus.hw.ex12_r_hw.models.User;
import ru.otus.hw.ex12_r_hw.repositories.UserRepository;
import ru.otus.hw.ex12_r_hw.security.CustomReactiveUserDetailsService;
import ru.otus.hw.ex12_r_hw.security.MethodSecurityConfiguration;
import ru.otus.hw.ex12_r_hw.security.SecurityConfiguration;
import ru.otus.hw.ex12_r_hw.services.UserService;

import static org.mockito.Mockito.when;

/**
 * Test class for the {@link UserController}
 */
@WebFluxTest({UserController.class})
@Import({ApplConfig.class,
        SecurityConfiguration.class,
        MethodSecurityConfiguration.class,
        CustomReactiveUserDetailsService.class})
public class UserControllerWebTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Autowired
    private Scheduler workerPool;

    @Autowired
    private WebTestClient webTestClient;


    @BeforeEach
    public void setup() {

    }

    @Test
    public void verif() throws Exception {
    }

/*    @WithMockUser(
            username = "USER55",
            authorities = {"ROLE_ADMIN"}
    )*/
    @Test
    public void getAll() throws Exception {

        User user1 = new User(1L, "Aa", "BB", "CC");
        User user2 = new User(2L, "dd", "ee", "ff");

        when(userRepository.findAll()).thenReturn(Flux.just(user1, user2));
        webTestClient.get().uri("/api/user")
                .exchange()
                .expectStatus().isOk();
    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
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

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void create() throws Exception {
/*        String userCreateDto = """
                {
                    "name": "",
                    "login": "",
                    "password": ""
                }""";

        mockMvc.perform(post("/api/user")
                        .content(userCreateDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());*/
    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void put() throws Exception {
/*        String userUpdateDto = """
                {
                    "name": "",
                    "login": "",
                    "password": ""
                }""";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{0}", "0")
                        .content(userUpdateDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());*/
    }

    @WithMockUser(
            username = "USER1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void delete() throws Exception {
/*        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{0}", "0"))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andDo(print());*/
    }
}
