package com.wfiis.CalculatorCO2.user.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String name) {
        super("Role: " + name + " could not be found.");
    }
}
