package com.wfiis.CalculatorCO2.user.assembler;

import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.Role;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAssembler {
    public UserAuthenticationPrincipal convertAuthenticationToModel(User user) {
        return UserAuthenticationPrincipal.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .isActive(user.getIsActive())
                .role(user.getRole())
                .build();
    }

    public User convertRegisterToModel(UserRegisterModel userRegisterModel) {
        return User.builder()
                .email(userRegisterModel.getEmail())
                .lastName(userRegisterModel.getLastName())
                .name(userRegisterModel.getName())
                .password(userRegisterModel.getPassword())
                // TODO: Change while implementing do request on register
                .isActive(true)
                .role(Role.USER)
                .build();
    }

    public UserProfileModel convertEntityToModel(User user) {
        return UserProfileModel.builder()
                .email(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }
}
