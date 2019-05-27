package com.wfiis.CalculatorCO2.leftover.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Leftover doesn't exist")
public class LeftoverNotFoundException extends RuntimeException {
    public LeftoverNotFoundException(Long leftoverId) {
        super("Leftover with id " + leftoverId + " doesn't exists");
    }
}
