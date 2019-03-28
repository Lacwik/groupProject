package com.wfiis.CalculatorCO2.security.assembler;

import com.wfiis.CalculatorCO2.metadata.entity.User;
import com.wfiis.CalculatorCO2.security.model.UserAuthenticationPrincipal;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationPrincipalAssembler {
    public UserAuthenticationPrincipal convertToModel(User user) {
        return UserAuthenticationPrincipal.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .isActive(user.getIsActive())
                .role(user.getRole())
                .build();
    }
}
