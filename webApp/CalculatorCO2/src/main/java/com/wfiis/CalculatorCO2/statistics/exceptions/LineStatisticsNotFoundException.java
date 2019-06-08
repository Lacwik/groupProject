package com.wfiis.CalculatorCO2.statistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "LineStatistics doesn't exist")
public class LineStatisticsNotFoundException extends RuntimeException {
    public LineStatisticsNotFoundException(Long lineStatisticsID) {
        super("StageResourceValue with id " + lineStatisticsID + " doesn't exists");
    }
}
