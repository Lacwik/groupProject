package com.wfiis.CalculatorCO2.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User doesn't exist.")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("User with email: " + email + " could not be found.");
    }
    public UserNotFoundException(Long id) {
        super("User with id: " + id + " could not be found.");
    }
}
