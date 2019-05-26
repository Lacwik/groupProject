package com.wfiis.CalculatorCO2.resource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource doesn't exist")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long resourceId) {
        super("Resource with id " + resourceId + " doesn't exists");
    }
}
