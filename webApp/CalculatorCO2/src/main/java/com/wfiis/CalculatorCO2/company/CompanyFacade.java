package com.wfiis.CalculatorCO2.company;

import com.wfiis.CalculatorCO2.company.metadata.CompanyAssembler;
import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.company.model.CompanyModel;
import com.wfiis.CalculatorCO2.company.model.CompanyRegisterModel;
import com.wfiis.CalculatorCO2.request.metadata.RegisterRequestService;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CompanyFacade {
    private final CompanyService companyService;
    private final CompanyAssembler companyAssembler;
    private final UserMetadataService userMetadataService;
    private final RegisterRequestService registerRequestService;

    @Transactional
    public CompanyModel registerCompany(CompanyRegisterModel registerModel) {
        // validate register model
        User user = registerUser(registerModel);

        Company company = registerCompany(registerModel, user);

        registerRequestService.addUserCompanyRegisterRequest(company, user);

        return companyAssembler.convertEntityToModel(company);
    }

    private Company registerCompany(CompanyRegisterModel registerModel, User user) {
        Company company = companyAssembler.convertRegisterModelToEntity(registerModel, user);
        return companyService.saveCompany(company);
    }

    private User registerUser(CompanyRegisterModel registerModel) {
        return userMetadataService.saveUser(registerModel);
    }

    @SecureCompanyScope(role = CompanyRole.EXPERT)
    public CompanyModel getCompany(CompanyIdentity company) {
        return companyAssembler.convertEntityToModel(companyService.findCompany(company.getCompanyId()));
    }
}
