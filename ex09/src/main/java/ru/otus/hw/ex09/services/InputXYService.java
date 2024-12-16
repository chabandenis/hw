package ru.otus.hw.ex09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.ex09.dto.InputXYDTO;

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

        if (verifString(inputXYDTO.getXFirst()) != true) {
            inputXYDTO.setXFirst(x1);
        }

        if (verifNumber(inputXYDTO.getYFirst()) != true) {
            inputXYDTO.setYFirst(y1);
        }

        if (verifString(inputXYDTO.getXSecond()) != true) {
            inputXYDTO.setXSecond(x2);
        }

        if (verifNumber(inputXYDTO.getYSecond()) != true) {
            inputXYDTO.setYSecond(y2);
        }
    }
}
