package ru.otus.hw.ex11.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 * Test class for the {@link GameController}
 */
@WebMvcTest({GameController.class})
public class GameControllerTest {
/*

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
*/
}
