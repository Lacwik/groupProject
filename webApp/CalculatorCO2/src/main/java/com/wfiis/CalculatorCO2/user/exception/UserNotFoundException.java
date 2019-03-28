package com.wfiis.CalculatorCO2.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("User with email: " + email + " could not be found.");
    }
    public UserNotFoundException(Long id) {
        super("User with id: " + id + " could not be found.");
    }
}
