package com.wfiis.CalculatorCO2.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserProfileModel {
    private String name;
    private String lastName;
    private String email;
}
