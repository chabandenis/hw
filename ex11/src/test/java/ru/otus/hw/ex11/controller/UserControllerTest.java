package ru.otus.hw.ex11.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 * Test class for the {@link UserController}
 */
@WebMvcTest({UserController.class})
public class UserControllerTest {
/*

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
        UserDto userIncorrenct = new UserDto(2L, "userXX", "userXX", "11");

        UserLoginActionDto userLoginActionDto = new UserLoginActionDto();
        userLoginActionDto.setLogin("user1");
        userLoginActionDto.setPassword("1");

        given(userService.findByLogin(any())).willReturn(userIncorrenct);
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

        User userParametr = new User();
        userParametr.setId(1l);
        userParametr.setName("userX");
        userParametr.setLogin("userX");
        userParametr.setPassword("2");

        UserDto user = UserMapper.toUserDto(userParametr);

        String userIn = """
                    {
                        "id": 1,
                        "name": "userX",
                        "login": "userX",
                        "password": "2"
                    }
                """;

        Mockito.when(userService.insert(any())).thenReturn(user);

        String expectedString = mapper.writeValueAsString(user);
        System.out.println(expectedString);

        mockMvc.perform(post("/api/users/insert")
                        .content(userIn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedString))
                .andDo(print())
        ;
    }

    @Test
    public void update() throws Exception {
        UserDto user = new UserDto(1L, "userX", "userX", "1");
        User userParametrGiven = new User();
        userParametrGiven.setName("userX");
        userParametrGiven.setId(1l);
        userParametrGiven.setLogin("userX");
        userParametrGiven.setPassword("1");

        String userIn = """
                {
                    "id": 1,
                    "name": "userX",
                    "login": "userX",
                    "password": "1"
                }""";

        //given(userService.update(any())).willReturn(user);
        given(userService.update(userParametrGiven)).willReturn(user);

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
*/
}
