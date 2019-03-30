package com.wfiis.CalculatorCO2.user.security.scopes;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.security.authorization.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ScopeAuthenticationAspect {
    private final UserAuthenticationProvider provider;
    private final CompanyRoleValidatorService validatorService;

    @Before("@annotation(scope)")
    public void validateCompanyScope(JoinPoint pjp, SecureCompanyScope scope) {
        CompanyIdentity companyIdentity = findCompanyIdentity(pjp);

        UserAuthenticationPrincipal principal = provider.getUserAuthenticalPrincipal();

        validatePrincipal(principal, scope);

        validateScope(principal, companyIdentity, scope);
    }

    private void validatePrincipal(UserAuthenticationPrincipal principal, SecureCompanyScope scope) {
        if (principal == null) {
            log.error("Cannot find a logged principal using @SecureCompanyScope annotation.");
            throw new UnauthorizedException("Couldn't authorize principal due to is null to scope: " + scope.role().name());
        }
    }

    private void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity, SecureCompanyScope scope) {
        try {
            validatorService.validateScope(principal, companyIdentity, scope.role());
        } catch (Exception e) {
            log.warn("User: {} isn't able to see company: {}, scope: {}", principal, companyIdentity.getCompanyId(), scope.role().name());
            throw e;
        }
    }

    private CompanyIdentity findCompanyIdentity(JoinPoint pjp) {
        for(Object arg : pjp.getArgs()) {
            if (arg instanceof CompanyIdentity) {
                return (CompanyIdentity) arg;
            }
        }

        log.error("Can't find a CompanyIdentity args in: {} while using a @SecureCompanyScope to secure method.", pjp.getArgs());
        throw new UnauthorizedException("You are not able to see this company resources.");
    }


}
