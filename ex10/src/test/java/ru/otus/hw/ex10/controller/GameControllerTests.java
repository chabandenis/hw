package ru.otus.hw.ex10.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.ex10.dto.ChessFairDto;
import ru.otus.hw.ex10.dto.GameDto;
import ru.otus.hw.ex10.dto.InputXYDTO;
import ru.otus.hw.ex10.dto.desk.ClmDto;
import ru.otus.hw.ex10.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex10.dto.fromWeb.WelcomeDto;
import ru.otus.hw.ex10.logic.Cache;
import ru.otus.hw.ex10.models.Game;
import ru.otus.hw.ex10.services.GameService;
import ru.otus.hw.ex10.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest({
        GameController.class,
        UserController.class,
        InputXYDTO.class, Cache.class
})
class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    UserService userService;

    private GameDto gameDto = new GameDto();

    @BeforeEach
    void setUp() {
        gameDto.setId(1l);

        ChessFairDto chessFairDto = new ChessFairDto();
        chessFairDto.setId(1l);

        List<RowOnTheDeskDto> desk = new ArrayList<>();
        RowOnTheDeskDto rowOnTheDeskDto = new RowOnTheDeskDto();

        Map<Integer, ClmDto> arr = new HashMap<>();
        ClmDto clmDto = new ClmDto("X", 1l);

        arr.put(0, clmDto);
        arr.put(1, clmDto);
        arr.put(2, clmDto);
        arr.put(3, clmDto);
        arr.put(4, clmDto);
        arr.put(5, clmDto);
        arr.put(6, clmDto);
        arr.put(7, clmDto);

        rowOnTheDeskDto.setArr(arr);
        rowOnTheDeskDto.setRightClm(" ");
        rowOnTheDeskDto.setLeftClm(" ");

        desk.add(rowOnTheDeskDto);

        chessFairDto.setDesk(desk);

        gameDto.setChessFair(chessFairDto);
//        gameDto.setUserBlack(new UserDto(1l, "user 1", "user1"));
//        gameDto.setUserNext(new UserDto(1l, "user 1", "user1"));
//        gameDto.setUserWhite(new UserDto(2l, "user 2", "user2"));
    }

    @Test
    public void edit() throws Exception {
        when(gameService.getOne(1L))
                .thenReturn(
                        gameDto
                );

        when(gameService.getOne(null))
                .thenReturn(
                        gameDto
                );

        doNothing().when(gameService).doStep(any(), any());

        InputXYDTO inputXYDTO = new InputXYDTO();
        inputXYDTO.setX1("a");
        inputXYDTO.setY1("1");
        inputXYDTO.setX2("a");
        inputXYDTO.setY2("5");

        mockMvc.perform(post("/edit")
                        .param("id", "1")
                        .param("xFirst", "a")
                        .param("yFirst", "1")
                        .param("xSecond", "a")
                        .param("ySecond", "5"))

                .andExpect(status().isOk())
                .andExpect(model().attribute("xys", inputXYDTO))
                .andExpect(view().name("list"))
                .andDo(print());
    }

    @Test
    public void getOne() throws Exception {
        when(gameService.getOne(1L))
                .thenReturn(
                        gameDto
                );
        when(gameService.getOne(null))
                .thenReturn(
                        gameDto
                );

        mockMvc.perform(get("/game").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                //.andExpect(model().attribute(""))
                .andDo(print())
        ;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void gameNew() throws Exception {
        Game game = new Game();
        when(gameService.newGame())
                .thenReturn(
                        game
                );

        GameDto gameDto1 = new GameDto();

        WelcomeDto welcomeDto = new WelcomeDto();
        welcomeDto.setName("Иванов Иван");

//        when(userService.getWelcome(any()))
//                .thenReturn(
//                        welcomeDto
//                );

        mockMvc.perform(post("/new")
                        .param("userId", "1"))
                .andExpect(view().name("redirect:/game?id=null"))
                // редирект подменяет модель(наверное), возвращается всегда null
//                .andExpect(model().attribute("game", gameDto1))
                .andDo(print());

    }

    @Test
    public void gameDelete() throws Exception {
        mockMvc.perform(post("/del")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void doStep() throws Exception {
        String inputXYDTO = """
                {
                
                }""";

        mockMvc.perform(post("/api/games/step")
                        .content(inputXYDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
