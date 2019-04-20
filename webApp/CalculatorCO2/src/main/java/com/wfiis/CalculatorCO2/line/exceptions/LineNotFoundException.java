package com.wfiis.CalculatorCO2.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Line doesn't exist")
public class LineNotFoundException extends RuntimeException {
    public LineNotFoundException(Long lineId) { super("Line with id " + lineId + " doesn't exist");
    }
}
