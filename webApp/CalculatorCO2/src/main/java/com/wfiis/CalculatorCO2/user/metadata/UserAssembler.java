package com.wfiis.CalculatorCO2.user.metadata;

import com.wfiis.CalculatorCO2.request.metadata.entity.UserRegisterRequestView;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.Role;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterRequestModel;
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

    public User convertRegisterToEntity(UserRegisterModel userRegisterModel) {
        return User.builder()
                .email(userRegisterModel.getEmail())
                .lastName(userRegisterModel.getLastName())
                .name(userRegisterModel.getName())
                .password(userRegisterModel.getPassword())
                .isActive(false)
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

    public List<UserProfileModel> convertEntityToModel(List<UserRegisterRequestView> all) {
        return all.stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    private UserRegisterRequestModel convertEntityToModel(UserRegisterRequestView userRegisterRequestView) {
        return UserRegisterRequestModel.registerModelBuilder()
                .email(userRegisterRequestView.getEmail())
                .lastName(userRegisterRequestView.getLastName())
                .name(userRegisterRequestView.getName())
                .id(userRegisterRequestView.getId())
                .build();
    }
}
