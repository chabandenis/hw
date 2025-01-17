package ru.otus.hw.ex11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex11.dto.InputXYDTO;

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

    public void verfif(InputXYDTO inputXYDTO) {

        if (verifString(inputXYDTO.getX1()) != true) {
            inputXYDTO.setX1(x1);
        }

        if (verifNumber(inputXYDTO.getY1()) != true) {
            inputXYDTO.setY1(y1);
        }

        if (verifString(inputXYDTO.getX2()) != true) {
            inputXYDTO.setX2(x2);
        }

        if (verifNumber(inputXYDTO.getY2()) != true) {
            inputXYDTO.setY2(y2);
        }
    }
}
