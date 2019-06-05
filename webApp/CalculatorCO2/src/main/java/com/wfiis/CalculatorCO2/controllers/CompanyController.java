package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.company.CompanyFacade;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.company.model.CompanyMember;
import com.wfiis.CalculatorCO2.company.model.CompanyModel;
import com.wfiis.CalculatorCO2.lineStatistics.LineStatisticsFacade;
import com.wfiis.CalculatorCO2.lineStatistics.metadata.entity.LineStatistics;
import com.wfiis.CalculatorCO2.lineStatistics.model.LineStatisticsCreateModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyFacade companyFacade;
    private final LineStatisticsFacade lineStatisticsFacade;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyModel> getCompany(@PathVariable(name = "id") Long companyId) {
        return ResponseEntity.ok(companyFacade.getCompany(CompanyIdentity.of(companyId)));
    }

    @GetMapping(value = "/{id}/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LineStatisticsCreateModel>> getCompanyStatistics(@PathVariable(name = "id") Long companyId) {
        return ResponseEntity.ok(lineStatisticsFacade.getStatisticsForCompany(CompanyIdentity.of(companyId)));
    }

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMemberToCompany(@PathVariable(name = "id") Long companyId, @RequestBody CompanyMember companyMember) {
        companyFacade.addMemberToCompany(CompanyIdentity.of(companyId), companyMember);

        return ResponseEntity.ok().build();
    }
}
