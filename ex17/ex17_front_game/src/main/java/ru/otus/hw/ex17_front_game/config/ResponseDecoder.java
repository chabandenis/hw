package ru.otus.hw.ex17_front_game.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw.ex17_front_game.model.ClientData;

import java.io.IOException;
import java.lang.reflect.Type;

public class ResponseDecoder implements Decoder {
    private static final Logger log = LoggerFactory.getLogger(ResponseDecoder.class);

    private final Decoder defaultDecoder;
    private final ObjectMapper mapper;

    public ResponseDecoder(Decoder defaultDecoder, ObjectMapper mapper) {
        this.defaultDecoder = defaultDecoder;
        this.mapper = mapper;
    }

    @Override
    public ClientData decode(Response response, Type type) throws IOException, FeignException {
        var responseAsString = (String) defaultDecoder.decode(response, String.class);
        log.info("response:{}", responseAsString);
        return mapper.readValue(responseAsString, ClientData.class);
    }
}
