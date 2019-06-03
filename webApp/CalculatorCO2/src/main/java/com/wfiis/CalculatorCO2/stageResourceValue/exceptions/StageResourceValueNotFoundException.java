package com.wfiis.CalculatorCO2.stageResourceValue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "StageResourceValue doesn't exist")
public class StageResourceValueNotFoundException extends RuntimeException {
    public StageResourceValueNotFoundException(Long srvID) {
        super("StageResourceValue with id " + srvID + " doesn't exists");
    }
}
