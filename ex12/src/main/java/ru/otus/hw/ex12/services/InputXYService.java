package ru.otus.hw.ex12.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex12.dto.game.CoordinatesDto;

@RequiredArgsConstructor
@Service
public class InputXYService {
    private String x1 = "Incorrect";

    private String y1 = "Incorrect";

    private String x2 = "Incorrect";

    private String y2 = "Incorrect";

    private boolean verifString(String val) {
        if (val.length() != 1) {
            return false;
        }

        val = val.toUpperCase();

        if (val.charAt(0) < 'A' || val.charAt(0) > 'H') {
            return false;
        }
        return true;
    }

    private boolean verifNumber(String val) {
        Integer retVal;

        // проверить, что это число
        try {
            retVal = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return false;
        }

        // проверить, что принадлежит интервалу
        if (retVal < 1 || retVal > 8) {
            return false;
        }

        return true;
    }

    public void verfif(CoordinatesDto coordinatesDTO) {

        if (verifString(coordinatesDTO.getX1()) != true) {
            coordinatesDTO.setX1(x1);
        }

        if (verifNumber(coordinatesDTO.getY1()) != true) {
            coordinatesDTO.setY1(y1);
        }

        if (verifString(coordinatesDTO.getX2()) != true) {
            coordinatesDTO.setX2(x2);
        }

        if (verifNumber(coordinatesDTO.getY2()) != true) {
            coordinatesDTO.setY2(y2);
        }
    }
}
