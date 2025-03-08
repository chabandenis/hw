package ru.otus.hw.ex16.service;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw.ex16.model.Caterpillar;
import ru.otus.hw.ex16.model.Egg;

import java.util.Collection;

@MessagingGateway
public interface EggGateway {

    @Gateway(requestChannel = "eggChannel", replyChannel = "caterpillarChannel")
    Collection<Caterpillar> fertilization(Collection<Egg> orderItem);
}
