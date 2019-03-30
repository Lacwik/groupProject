package com.wfiis.CalculatorCO2.user.security.scopes;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.security.scopes.validator.CompanyRoleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyRoleValidatorService {
    private final List<CompanyRoleValidator> validators;

    void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity, CompanyRole scope) {
        validators.forEach(validator -> {
            if (validator.getScopeResponsibleFor() == scope) {
                validator.validateScope(principal, companyIdentity);
            }
        });
    }
}
