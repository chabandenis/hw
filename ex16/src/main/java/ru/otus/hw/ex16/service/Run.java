package ru.otus.hw.ex16.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.ex16.model.Egg;

import java.util.List;

/*
        Egg egg = new Egg(null, "egg 001");
        Butterfly butterfly = new Butterfly(null, "Butterfly 001");
        Grass grass = new Grass(null, "Grass 001");
        Sun sun = new Sun(null, "sun 001");
*/

//  1.
//    Яйца (гусеницы) + бабочка мальчик => Гусеница
//        var caterpillar = eggService.fertilization(egg, butterfly);

//  2. гусеницы + трава => куколки
//        var pupae = caterpillarService.growing(caterpillar, grass);

//  3.   куколки => бабочки
//        var butterfly2 = pupaeService.growing(pupae);

// 4.   бабочка+солнце => яйца
//        var eggs = butterflyService.growing(butterfly2, sun);

@AllArgsConstructor
@Component
public class Run {

    private final EggGateway eggGateway;

    public void run() throws Exception {
        System.out.println("start");

        eggGateway.fertilization(List.of(
                new Egg(null, "Яйцо 001"),
                new Egg(null, "Яйцо 002")));
    }
}
