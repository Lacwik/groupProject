package com.wfiis.CalculatorCO2.vegetable.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Vegetable doesn't exist")
public class VegetableNotFoundException extends RuntimeException {
    public VegetableNotFoundException(Long vegetableId) {
        super("Vegetable with id " + vegetableId + " doesn't exists");
    }
}
