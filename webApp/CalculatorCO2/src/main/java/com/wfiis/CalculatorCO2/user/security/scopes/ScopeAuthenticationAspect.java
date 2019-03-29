package com.wfiis.CalculatorCO2.user.security.scopes;

import com.wfiis.CalculatorCO2.company.CompanyFacade;
import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ScopeAuthenticationAspect {
    private final UserAuthenticationProvider provider;
    private final CompanyFacade companyFacade;

    @Around("@annotation(CompanyExpert)")
    public void validateCompanyScopeForExpert(ProceedingJoinPoint pjp) throws Throwable {
        CompanyIdentity companyIdentity = findCompanyIdentity(pjp);

        UserAuthenticationPrincipal principal = provider.getUserAuthenticalPrincipal();

        if (principal == null) {
            log.error("Cannot find a logged principal using @CompanyExpert annotation.");
            throw new RuntimeException();
        }

        if (!companyFacade.isExpertOfCompany(principal.getUsername(), companyIdentity.getCompanyId())) {
            throw new RuntimeException();
        }

        pjp.proceed();
    }

    private CompanyIdentity findCompanyIdentity(ProceedingJoinPoint pjp) {
        for(Object arg : pjp.getArgs()) {
            if (arg instanceof CompanyIdentity) {
                return (CompanyIdentity) arg;
            }
        }

        throw new RuntimeException("Unauthorized");
    }


}
