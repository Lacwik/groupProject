package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.company.CompanyFacade;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.company.model.CompanyModel;
import com.wfiis.CalculatorCO2.company.model.CompanyRegisterRequestModel;
import com.wfiis.CalculatorCO2.user.UserFacade;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class RegisterRequestController {
    private final CompanyFacade companyFacade;
    private final UserFacade userFacade;

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyRegisterRequestModel>> getCompanyRegisterRequests() {
        return ResponseEntity.ok(companyFacade.getCompanyRegisterRequests());
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserProfileModel>> getUserRegisterRequests() {
        List<UserProfileModel> userRegisterRequests = userFacade.getUserRegisterRequests();
        return ResponseEntity.ok(userRegisterRequests);
    }
}
