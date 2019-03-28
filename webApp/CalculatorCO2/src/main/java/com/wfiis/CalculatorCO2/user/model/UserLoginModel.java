package com.wfiis.CalculatorCO2.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserLoginModel {
    private String email;
    private String password;
}
