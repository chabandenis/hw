package ru.otus.hw.ex09;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.ex09.controller.GameController;
import ru.otus.hw.ex09.dto.ChessFairDto;
import ru.otus.hw.ex09.dto.GameDto;
import ru.otus.hw.ex09.dto.InputXYDTO;
import ru.otus.hw.ex09.dto.UserDto;
import ru.otus.hw.ex09.dto.desk.ClmDto;
import ru.otus.hw.ex09.dto.desk.RowOnTheDeskDto;
import ru.otus.hw.ex09.services.GameService;
import ru.otus.hw.ex09.services.InputXYService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest({GameController.class,
        InputXYDTO.class,
        InputXYService.class
})
class Ex09ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void doStuffMethod2() throws Exception {
        mockMvc.perform(post("/do-stuff2")
                        .param("id", "0")
                        .param("userBlack", """
                                {
                                    "id": 0,
                                    "name": ""
                                }""")
                        .param("userWhite", """
                                {
                                    "id": 0,
                                    "name": ""
                                }""")
                        .param("userNext", """
                                {
                                    "id": 0,
                                    "name": ""
                                }""")
                        .param("chessFair", """
                                {
                                    "id": 0,
                                    "desk": [],
                                    "positionInChessFairDtos": []
                                }""")
                        .param("xFirst", "")
                        .param("yFirst", "")
                        .param("xSecond", "")
                        .param("ySecond", ""))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getOne() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setId(1l);

        ChessFairDto chessFairDto = new ChessFairDto();
        chessFairDto.setId(1l);

        List<RowOnTheDeskDto> desk = new ArrayList<>();
        RowOnTheDeskDto rowOnTheDeskDto = new RowOnTheDeskDto();

        Map<Integer, ClmDto> arr = new HashMap<>();
        ClmDto clmDto = new ClmDto("X", 1l);

        arr.put(0, clmDto);

        rowOnTheDeskDto.setArr(arr);
        rowOnTheDeskDto.setRightClm(" ");
        rowOnTheDeskDto.setLeftClm(" ");

        desk.add(rowOnTheDeskDto);

        chessFairDto.setDesk(desk);

        gameDto.setChessFair(chessFairDto);
        gameDto.setUserBlack(new UserDto(1l, "user 1"));
        gameDto.setUserNext(new UserDto(1l, "user 1"));
        gameDto.setUserWhite(new UserDto(2l, "user 2"));


        when(gameService.getOne(1L))
                .thenReturn(
                        gameDto
                );

        mockMvc.perform(get("/game/1", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
//                .andExpect(model().attribute(""))
                .andDo(print());
    }

    @Test
    void contextLoads() {
    }


}
