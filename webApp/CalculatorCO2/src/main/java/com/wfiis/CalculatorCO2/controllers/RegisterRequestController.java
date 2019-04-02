package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.request.RegisterRequestFacade;
import com.wfiis.CalculatorCO2.request.model.CompanyRegisterRequestModel;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class RegisterRequestController {
    private final RegisterRequestFacade registerRequestFacade;

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyRegisterRequestModel>> getCompanyRegisterRequests() {
        return ResponseEntity.ok(registerRequestFacade.getUserCompanyRegisterRequests());
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserProfileModel>> getUserRegisterRequests() {
        return ResponseEntity.ok(registerRequestFacade.getUserRegisterRequests());
    }

    @PutMapping(value = "/company/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> acceptUserCompanyRequest(@PathVariable(name = "id") Long companyId) {
        registerRequestFacade.acceptUserCompanyRequest(companyId);
        return ResponseEntity.ok("Company register request accepted.");
    }

    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> acceptUserRequest(@PathVariable Long id) {
        registerRequestFacade.acceptUserRequest(id);
        return ResponseEntity.ok("User register request accepted.");
    }
}
