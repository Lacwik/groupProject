package com.wfiis.CalculatorCO2.user.security.scopes.validator;

import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyExpertRoleValidator implements CompanyRoleValidator {
    private final CompanyService companyService;

    @Override
    public void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity) {
        boolean exist = companyService.isExpertOfCompany(principal.getId(), companyIdentity.getCompanyId());

        if (!exist) {
            throw new RuntimeException();
        }
    }

    @Override
    public CompanyRole getScopeResponsibleFor() {
        return CompanyRole.EXPERT;
    }
}
