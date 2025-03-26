package ru.otus.hw.ex17_front_game.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class RequestEncoder implements Encoder {
    private static final Logger log = LoggerFactory.getLogger(RequestEncoder.class);
    private final Encoder defaultEncoder;

    public RequestEncoder(Encoder defaultEncoder) {
        this.defaultEncoder = defaultEncoder;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        log.info("encode value:{}", object);
        defaultEncoder.encode(object, bodyType, template);
    }
}
