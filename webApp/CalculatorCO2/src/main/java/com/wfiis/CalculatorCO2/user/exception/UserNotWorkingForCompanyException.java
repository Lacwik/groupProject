package com.wfiis.CalculatorCO2.user.exception;

public class UserNotWorkingForCompanyException extends RuntimeException {
    public UserNotWorkingForCompanyException(Long userId) {
        super("User with id " + userId + "not working for company");
    }
}
