package com.wfiis.CalculatorCO2.stage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Stage doesn't exist")
public class StageNotFoundException extends RuntimeException {
    public StageNotFoundException(Long stageId) {
        super("Stage with id " + stageId + " doesn't exists");
    }
}
