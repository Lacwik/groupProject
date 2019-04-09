package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.user.UserFacade;
import com.wfiis.CalculatorCO2.user.model.CompanyRoleModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company-auth")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class CompanyAuthenticationRoleController {
    private final UserFacade userFacade;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyRoleModel>> getCompany(UsernamePasswordAuthenticationToken authModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) authModel.getPrincipal();
        return ResponseEntity.ok(userFacade.getAllUserRoles(principal.getId()));
    }
}
