package com.wfiis.CalculatorCO2.user.metadata;

import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.Role;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import com.wfiis.CalculatorCO2.user.model.UserSimpleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserAssembler {
    public UserAuthenticationPrincipal convertAuthenticationToModel(User user) {
        return UserAuthenticationPrincipal.builder()
                .email(user.getEmail())
                .id(user.getId())
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

    public UserSimpleModel convertEntityToSimpleModel(User user) {
        return UserSimpleModel.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }

    public List<UserSimpleModel> convertEntitiesToSimpleModel(List<User> users) {
        return users.stream()
                .map(this::convertEntityToSimpleModel)
                .collect(Collectors.toList());
    }
}
