package com.wfiis.CalculatorCO2.user.security.scopes.validator;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;

public interface CompanyRoleValidator {
    void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity);
    CompanyRole getScopeResponsibleFor();
}
