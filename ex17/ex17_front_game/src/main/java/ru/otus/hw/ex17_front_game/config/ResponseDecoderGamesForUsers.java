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
    private static final Logger LOG = LoggerFactory.getLogger(ResponseDecoderGamesForUsers.class);

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
        LOG.info("response:{}", responseAsString);

        List<GameDto> gameDtos = mapper.readValue(responseAsString, new TypeReference<List<GameDto>>() {});
        return Flux.fromIterable(gameDtos);
    }

}
