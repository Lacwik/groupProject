package com.wfiis.CalculatorCO2.stage.exceptions;

public class StageNotFoundException extends RuntimeException {
    public StageNotFoundException(Long stageId) {
        super("Stage with id " + stageId + " doesn't exists");
    }
}
