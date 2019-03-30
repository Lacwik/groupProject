package com.wfiis.CalculatorCO2.company;

import com.wfiis.CalculatorCO2.company.metadata.CompanyAssembler;
import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.company.model.CompanyModel;
import com.wfiis.CalculatorCO2.company.model.CompanyRegisterModel;
import com.wfiis.CalculatorCO2.user.UserFacade;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CompanyFacade {
    private final CompanyService companyService;
    private final CompanyAssembler companyAssembler;
    private final UserFacade userFacade;
    private final UserMetadataService userMetadataService;

    @Transactional
    public CompanyModel registerCompany(CompanyRegisterModel registerModel) {
        // validate register model
        User user = registerUser(registerModel);

        Company company = companyAssembler.convertRegisterModelToEntity(registerModel, user);

        return companyAssembler.convertEntityToModel(companyService.saveCompany(company));
    }

    private User registerUser(CompanyRegisterModel registerModel) {
        UserProfileModel profileModel = userFacade.registerUser(registerModel);

        return userMetadataService.findUser(profileModel.getEmail());
    }

    @SecureCompanyScope(role = CompanyRole.EXPERT)
    public CompanyModel getCompany(CompanyIdentity company) {
        return companyAssembler.convertEntityToModel(companyService.findCompany(company.getCompanyId()));
    }

}
