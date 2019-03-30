package com.wfiis.CalculatorCO2.user.security.scopes.validator;

import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.security.authorization.UnauthorizedException;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyAdminRoleValidator implements CompanyRoleValidator {
    private final CompanyService companyService;

    @Override
    public void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity) {
        boolean exists = companyService.isAdminOfCompany(principal.getId(), companyIdentity.getCompanyId());

        if (!exists) {
            log.warn("User: {} is unauthorized due to he's not a company admin. Company: {}", principal, companyIdentity);
            throw new UnauthorizedException("You are not an administrator of this company.");
        }
    }

    @Override
    public CompanyRole getScopeResponsibleFor() {
        return CompanyRole.ADMIN;
    }
}
