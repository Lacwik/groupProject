package com.wfiis.CalculatorCO2.user.security.scopes;

import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAuthenticatedProvider {
    public UserAuthenticationPrincipal getUserAuthenticalPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return authentication == null ? null : (UserAuthenticationPrincipal) authentication.getPrincipal();
        } catch (Exception e) {
            log.warn("Caught exception when trying to infer authentication principal", e);
            return null;
        }
    }
}
