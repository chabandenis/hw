package ru.otus.hw.ex17_front_game.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import ru.otus.hw.ex17_front_game.dto.GameDto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ResponseDecoderGamesForUsers implements Decoder {
    private static final Logger log = LoggerFactory.getLogger(ResponseDecoderGamesForUsers.class);

    private final Decoder defaultDecoder;
    private final ObjectMapper mapper;

    public ResponseDecoderGamesForUsers(Decoder defaultDecoder, ObjectMapper mapper) {
        this.defaultDecoder = defaultDecoder;
        this.mapper = mapper;
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public Flux<GameDto> decode(Response response, Type type) throws IOException, FeignException {
        var responseAsString = (String) defaultDecoder.decode(response, String.class);
        log.info("response:{}", responseAsString);
        //return mapper.readValue(responseAsString, new TypeReference<Flux<GameDto>>() {});

        List<GameDto> gameDtos = mapper.readValue(responseAsString, new TypeReference<List<GameDto>>() {});
        return Flux.fromIterable(gameDtos);
    }

/*    @Override
    public Flux<GameDto> decode(Response response, Type type) throws IOException, FeignException {
        var responseAsString = ( Flux<GameDto>) defaultDecoder.decode(response, GameDto.class);
        log.info("response:{}", responseAsString);
        //return mapper.readValue(responseAsString, GameDto.class);*/


/*        TypeReference<List<GameDto>> jacksonTypeReference = new TypeReference<List<GameDto>>() {
        };

        Flux<GameDto> jacksonList = mapper.readValue(response.body().toString(), jacksonTypeReference);*/
/*        return responseAsString;
    }*/

/*
    public Flux<GameDto> decode(Response response, Type type) throws IOException, FeignException {
        // Получаем ответ в виде строки или потока байтов
        Flux<GameDto> responseAsString = (Flux<GameDto>) defaultDecoder.decode(response, GameDto.class);

        // Используем mapper для преобразования каждой строки в объект GameDto
        Flux<GameDto> gameDtos = responseAsString.flatMap(jsonString -> {
            try {
                return Flux.just(mapper.readValue(jsonString, GameDto.class));
            } catch (IOException e) {
                // Обработка ошибки при преобразовании JSON в объект
                throw new FeignException(e.getMessage(), response);
            }
        });

        log.info("response:{}", gameDtos);
        return gameDtos;
    }
*/

}
