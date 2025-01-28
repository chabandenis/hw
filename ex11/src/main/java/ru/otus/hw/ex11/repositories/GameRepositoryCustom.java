package ru.otus.hw.ex11.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.spi.Readable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.hw.ex11.dto.ChessFairDto;
import ru.otus.hw.ex11.dto.GameDto;
import ru.otus.hw.ex11.dto.PositionInChessFairInDbDto;
import ru.otus.hw.ex11.dto.UserDto;
import ru.otus.hw.ex11.models.ChessFair;
import ru.otus.hw.ex11.models.Figura;
import ru.otus.hw.ex11.models.PositionInChessFair;
import ru.otus.hw.ex11.services.GameService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Repository
@Slf4j
public class GameRepositoryCustom {

    private final R2dbcEntityTemplate template;

    private final ObjectMapper objectMapper;

    private final GameService gameService;

    private final ChessFairRepository chessFairRepository;

    private final FiguraRepository figuraRepository;


    private static final String SQL_ALL = """
            select
            	g.id,
            	g.date_game,
            	u_black.id black_id,
            	u_black."name" black_name,
            	u_black.login black_login,
            	u_black."password" black_password,
            	u_white.id white_id,
            	u_white."name" white_name,
            	u_white.login white_login,
            	u_white."password" white_password,
            	u_next.id next_id,
            	u_next."name" next_name,
            	u_next.login next_login,
            	u_next."password" next_password,
            	cf.id cf_id,
            	-- для удобства обработки json объекта из строки создаю в нужном виде. Решается проблема. получения недостающих данных из бд.	
            	JSON_AGG(picf3) picf_json
            from
            	games g
            left join users u_black on
            	g.user_black_id = u_black.id
            left join users u_white on
            	g.user_white_id = u_white.id
            left join users u_next on
            	g.user_next_id = u_next.id
            inner join chess_fairs cf on
            	g.chess_fair_id = cf.id
            left join position_in_chess_fairs picf on
            	picf.chess_fair_id = cf.id,
            	lateral (
            	select
            		picf2.id as id,
            		picf2.position_x as "positionX",
            		picf2.position_y as "positionY",
            		picf2.chess_fair_id as "chessFairId",
            		picf2.figura_id as "figuraId",
            		f."name" as "figuraName"
            	from
            		position_in_chess_fairs picf2
            	inner join figuras f on
            		f.id = picf2.figura_id		
            		) picf3
            where
            	picf3.id = picf.id
            	and g.user_black_id in ($1, $2)
            	and g.user_white_id in ($1, $2)
            group by
            	g.id,
            	g.date_game,
            	u_black.id,
            	u_black."name",
            	u_black.login,
            	u_black."password",
            	u_white.id,
            	u_white."name",
            	u_white.login,
            	u_white."password",
            	u_next.id,
            	u_next."name",
            	u_next.login,
            	u_next."password",
            	cf.id          
            """;

    public Flux<GameDto> findAll(Long mainUser, Long secondUser) {
        return template.getDatabaseClient().inConnectionMany(connection ->
                Flux.from(connection.createStatement(SQL_ALL)
                                .bind("$1", mainUser)
                                .bind("$2", secondUser)
                                .execute())
                        .flatMap(result -> result.map(this::mapper)));
    }

    private GameDto mapper(Readable selectedRecord) {
        GameDto gameDto = new GameDto();
        gameDto.setDateGame(selectedRecord.get("date_game", LocalDateTime.class));
        gameDto.setId(selectedRecord.get("id", Long.class));

        UserDto userBlack = new UserDto(
                selectedRecord.get("black_id", Long.class),
                selectedRecord.get("black_name", String.class),
                selectedRecord.get("black_login", String.class),
                selectedRecord.get("black_password", String.class)
        );
        gameDto.setUserBlack(userBlack);

        UserDto userWhite = new UserDto(
                selectedRecord.get("white_id", Long.class),
                selectedRecord.get("white_name", String.class),
                selectedRecord.get("white_login", String.class),
                selectedRecord.get("white_password", String.class)
        );
        gameDto.setUserWhite(userWhite);

        UserDto userNext = new UserDto(
                selectedRecord.get("next_id", Long.class),
                selectedRecord.get("next_name", String.class),
                selectedRecord.get("next_login", String.class),
                selectedRecord.get("next_password", String.class)
        );
        gameDto.setUserNext(userNext);

        var picf_json = selectedRecord.get("picf_json", String.class);
        try {
            // класс в виде полностью идентичном хранению данных в БД
            List<PositionInChessFairInDbDto> chessFairsInDb =
                    objectMapper.readValue(picf_json, new TypeReference<List<PositionInChessFairInDbDto>>() {
                    });

            List<PositionInChessFair> chessFairs = chessFairsInDb.stream()
                    .map(x -> new PositionInChessFair(
                            x.getId(),
                            x.getPositionX(),
                            x.getPositionY(),
                            new ChessFair(x.getChessFairId()),
                            //chessFairRepository.findById(x.getChessFairId()).block(),
                            new Figura(x.getFiguraId(), x.getFiguraName())/*figuraRepository.findById(x.getFiguraId()).block()*/
                    )).collect(Collectors.toList());

            log.debug("chessFairs: " + chessFairs.toString());

            ChessFairDto chessFairDto = new ChessFairDto();
            chessFairDto.setId(selectedRecord.get("cf_id", Long.class));
            chessFairDto.setDesk(gameService.fillFigureOnTheDesk(chessFairs));

            gameDto.setChessFair(chessFairDto);

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("notes:" + picf_json + " parsing error:" + e);
        }


        return gameDto;
    }


//        var notesAsText = selectedRecord.get("notes", String.class);
//        try {
//            List<String> notes = objectMapper.readValue(notesAsText, new TypeReference<>() {
//            });

/*
        gameDto.setChessFair(selectedRecord.get("chess_fair_id", ChessFairDto.class));
        gameDto.setUserBlack(selectedRecord.get("person_id", UserDto.class));
        gameDto.setUserNext(selectedRecord.get("person_id", UserDto.class));
        gameDto.setUserWhite(selectedRecord.get("person_id", UserDto.class));
*/

//        return gameDto;

//        } catch (JsonProcessingException e) {
//            throw new IllegalArgumentException("notes:" + /*notesAsText +*/ " parsing error:" + e);
//        }


    //    @Query("""
//            select g from Game g
//            where (g.userWhite.id = :user1 and g.userBlack.id = :user2 ) or
//                        (g.userWhite.id = :user2 and g.userBlack.id = :user1)
//            order by g.dateGame DESC""")
//    List<Game> findByUserId1AndUserId2OrderByDateGameDesc(
//            @Param("user1") Long user1,
//            @Param("user2") Long user2);

    //    @EntityGraph(value = "game-graph")
//    @Query("select g from Game g where g.userBlack.id = :id or g.userWhite.id = :id order by g.id DESC")
//    List<Game> findByUserBlackIdOrUserWhiteIdOrderByIdDesc(@Param("id") Long id);


    //    @EntityGraph(value = "game-graph")
//    List<Game> findByUserBlackId(Long id);

    //    @EntityGraph(value = "game-graph")
//    List<Game> findByUserWhiteId(Long id);

//    @EntityGraph(value = "game-graph")
//    @Override
//    Optional<Game> findById(Long aLong);


}
