package com.wfiis.CalculatorCO2.user.security.scopes.validator;

import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.security.authorization.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyExpertRoleValidator implements CompanyRoleValidator {
    private final CompanyService companyService;

    @Override
    public void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity) {
        boolean exist = companyService.isExpertOfCompany(principal.getId(), companyIdentity.getCompanyId());

        if (!exist) {
            log.warn("User: {} is unauthorized due to he's not a company expert. Company: {}", principal, companyIdentity);
            throw new ForbiddenException("You are not an expert of this company.");
        }
    }

    @Override
    public CompanyRole getScopeResponsibleFor() {
        return CompanyRole.EXPERT;
    }
}
