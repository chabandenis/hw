package ru.otus.hw.ex10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.ex10.dto.GameDto;
import ru.otus.hw.ex10.services.GameService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void getAll() throws Exception {
        List<GameDto> games = List.of(game);

        given(gameService.getAllByUsers(1l, 2l)).willReturn(games);

        mockMvc.perform(get("/api/games/{0}/{1}", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(games)))
                .andDo(print());
    }

    @Test
    public void doStep() throws Exception {
        String inputXYDTO = """
                {
                    "gameId": 0,
                    "x1": "",
                    "y1": "",
                    "x2": "",
                    "y2": ""
                }""";

        given(gameService.doStep(any())).willReturn(game);

        mockMvc.perform(post("/api/games/step")
                        .content(inputXYDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(game)))
                .andDo(print());
    }

    @Test
    public void newGame() throws Exception {
        given(gameService.newGame(0l, 0l)).willReturn(game);

        mockMvc.perform(post("/api/games/new/{0}/{1}", "0", "0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(game)))
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        given(gameService.delete(any())).willReturn(game);

        mockMvc.perform(post("/api/games/delete/{0}", "0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(game)))
                .andDo(print());
    }

    @Test
    public void getOne() throws Exception {
        given(gameService.getOne(any())).willReturn(game);

        mockMvc.perform(get("/api/games/{0}", "0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(game)))
                .andDo(print());
    }

    @Test
    public void test() throws Exception {

    }
}
