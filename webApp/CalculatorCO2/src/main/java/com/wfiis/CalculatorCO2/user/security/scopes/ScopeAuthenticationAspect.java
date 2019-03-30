package com.wfiis.CalculatorCO2.user.security.scopes;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
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
    // TODO: Add Unauthorized Exception and add @ExceptionHandler spring to return 401 status code

    @Before("@annotation(scope)")
    public void validateCompanyScope(JoinPoint pjp, SecureCompanyScope scope) {
        CompanyIdentity companyIdentity = findCompanyIdentity(pjp);

        UserAuthenticationPrincipal principal = provider.getUserAuthenticalPrincipal();

        validatePrincipal(principal);

        validateScope(principal, companyIdentity, scope);
    }

    private void validatePrincipal(UserAuthenticationPrincipal principal) {
        if (principal == null) {
            log.error("Cannot find a logged principal using @SecureCompanyScope annotation.");
            throw new RuntimeException();
        }
    }

    private void validateScope(UserAuthenticationPrincipal principal, CompanyIdentity companyIdentity, SecureCompanyScope scope) {
        try {
            validatorService.validateScope(principal, companyIdentity, scope.role());
        } catch (Exception e) {
            log.error("msg");
            throw e;
        }
    }

    private CompanyIdentity findCompanyIdentity(JoinPoint pjp) {
        for(Object arg : pjp.getArgs()) {
            if (arg instanceof CompanyIdentity) {
                return (CompanyIdentity) arg;
            }
        }

        throw new RuntimeException("Unauthorized");
    }


}
