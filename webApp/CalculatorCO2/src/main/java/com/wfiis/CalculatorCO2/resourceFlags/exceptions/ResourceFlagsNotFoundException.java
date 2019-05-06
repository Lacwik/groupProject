package com.wfiis.CalculatorCO2.resourceFlags.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Module doesn't exist")
public class ResourceFlagsNotFoundException extends RuntimeException {
    public ResourceFlagsNotFoundException(Long resourceFlagsId) {
        super("ResourceFlags with id " + resourceFlagsId + " doesn't exists");
    }
}
