package com.wfiis.CalculatorCO2.module.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Module doesn't exist")
public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException(Long moduleId) {
        super("Module with id " + moduleId + " doesn't exists");
    }
}
