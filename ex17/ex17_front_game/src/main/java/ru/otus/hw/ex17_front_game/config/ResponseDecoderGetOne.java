package ru.otus.hw.ex17_front_game.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import ru.otus.hw.ex17_front_game.dto.GameDto;

import java.io.IOException;
import java.lang.reflect.Type;

public class ResponseDecoderGetOne implements Decoder {
    private static final Logger LOG = LoggerFactory.getLogger(ResponseDecoderGetOne.class);

    private final Decoder defaultDecoder;

    private final ObjectMapper mapper;

    public ResponseDecoderGetOne(Decoder defaultDecoder, ObjectMapper mapper) {
        this.defaultDecoder = defaultDecoder;
        this.mapper = mapper;
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public Mono<ResponseEntity<GameDto>> decode(Response response, Type type) throws IOException, FeignException {
        var responseAsString = (String) defaultDecoder.decode(response, String.class);
        LOG.info("response:{}", responseAsString);

        GameDto gameDto = mapper.readValue(responseAsString, GameDto.class);
        return Mono.just(new ResponseEntity<>(gameDto, HttpStatus.OK));
    }

}
