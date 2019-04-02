package com.wfiis.CalculatorCO2.user.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequestModel extends UserProfileModel {
    private final Long id;

    @Builder(builderMethodName = "registerModelBuilder")
    public UserRegisterRequestModel(String name, String lastName, String email, Long id) {
        super(name, lastName, email);
        this.id = id;
    }
}
