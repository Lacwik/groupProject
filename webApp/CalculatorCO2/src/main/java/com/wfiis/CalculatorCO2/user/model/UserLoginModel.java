package com.wfiis.CalculatorCO2.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserLoginModel {
    private final String email;
    private final String password;
}
