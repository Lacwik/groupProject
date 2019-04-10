package com.wfiis.CalculatorCO2.user.security.scopes.validator;

import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.security.authorization.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyMemberRoleValidator implements CompanyRoleValidator {
    private final UserMetadataService userMetadataService;

    @Override
    public void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity) {
        boolean exists = userMetadataService.canUserWorkingForCompany(principal.getId(), companyIdentity.getCompanyId());

        if (!exists) {
            log.warn("User: {} is unauthorized due to he's not a member admin. Company: {}", principal, companyIdentity);
            throw new ForbiddenException("You are not a member of this company.");
        }
    }


    @Override
    public CompanyRole getScopeResponsibleFor() {
        return CompanyRole.MEMBER;
    }
}
