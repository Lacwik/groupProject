package com.wfiis.CalculatorCO2.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class UserRegisterModel {
    private final String name;
    private final String lastName;
    private final String email;
    private final String password;
}
