package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.company.CompanyFacade;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.company.model.CompanyModel;
import com.wfiis.CalculatorCO2.company.model.CompanyRegisterModel;
import com.wfiis.CalculatorCO2.user.UserFacade;
import com.wfiis.CalculatorCO2.user.model.UserLoginModel;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import com.wfiis.CalculatorCO2.user.security.scopes.CompanyExpert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyFacade companyFacade;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyModel> getCompany(@PathVariable(name = "id") Long companyId) {
        return ResponseEntity.ok(companyFacade.getCompany(CompanyIdentity.of(companyId)));

    }
}
