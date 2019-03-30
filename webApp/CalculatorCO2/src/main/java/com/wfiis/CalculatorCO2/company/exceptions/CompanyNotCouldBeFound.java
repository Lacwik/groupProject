package com.wfiis.CalculatorCO2.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Company doesn't exist.")
public class CompanyNotCouldBeFound extends RuntimeException{
    public CompanyNotCouldBeFound(Long companyId) {
        super("Company with id: " + companyId + " doesn't exist.");
    }
}
