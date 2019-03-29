package com.wfiis.CalculatorCO2.company.exceptions;

public class CompanyNotCouldBeFound extends RuntimeException{
    public CompanyNotCouldBeFound(Long companyId) {
        super("Company with id: " + companyId + " doesn't exist.");
    }
}
