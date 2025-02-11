package ru.otus.hw.ex12hw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.hw.ex12hw.dto.GameDto;
import ru.otus.hw.ex12hw.dto.game.GamesCreateDto;
import ru.otus.hw.ex12hw.services.GameService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link GameController}
 */
@WebMvcTest({GameController.class})
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Autowired
    private ObjectMapper mapper;

    private GameDto game;

    @BeforeEach
    public void setup() {
        game = new GameDto();
        game.setDateGame(LocalDateTime.now());
        game.setId(1l);
        game.setChessFair(null);
        game.setUserWhite(null);
        game.setUserNext(null);
        game.setUserBlack(null);
    }

    @Test
    public void test() throws Exception {

    }

    @Test
    public void getGamesForUsers() throws Exception {
        List<GameDto> games = List.of(game);
        given(gameService.getGamesForUsers(1l, 2l)).willReturn(games);
        mockMvc.perform(get("/api/game/{0}/{1}", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(games)))
                .andDo(print());
    }

    @Test
    public void step() throws Exception {
        String coordinatesDto = """
                {
                    "x1": "",
                    "y1": "",
                    "x2": "",
                    "y2": ""
                }""";

        given(gameService.step(any(), any())).willReturn(game);

        mockMvc.perform(put("/api/game/{0}", "0")
                        .content(coordinatesDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(game)))
                .andDo(print());
    }

    @Test
    public void create() throws Exception {
        String gamesCreateDtoStr = """
                {
                    "mainUser": 1,
                    "secondUser": 2
                }""";

        GamesCreateDto gamesCreateDto = new GamesCreateDto(1l, 2l);

        given(gameService.create(gamesCreateDto)).willReturn(game);

        mockMvc.perform(post("/api/game")
                        .content(gamesCreateDtoStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(game)))
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        given(gameService.delete(any())).willReturn(game);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/game/{0}", "0"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void getOne() throws Exception {
        given(gameService.getOne(any())).willReturn(game);

        mockMvc.perform(get("/api/game/{0}", "0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(game)))
                .andDo(print());
    }
}
