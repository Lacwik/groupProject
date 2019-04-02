package com.wfiis.CalculatorCO2.user.metadata;

import com.wfiis.CalculatorCO2.request.metadata.entity.UserRegisterRequestView;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRegisterRequestAssembler {
    public UserProfileModel convertEntityToModel(UserRegisterRequestView requestView) {
        return UserProfileModel.builder()
                .lastName(requestView.getLastName())
                .name(requestView.getName())
                .email(requestView.getEmail())
                .build();
    }

    public List<UserProfileModel> convertEntityToModel(List<UserRegisterRequestView> requestViews) {
        return requestViews.stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }
}
