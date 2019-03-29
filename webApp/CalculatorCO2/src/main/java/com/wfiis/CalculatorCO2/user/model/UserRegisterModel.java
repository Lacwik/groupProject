package com.wfiis.CalculatorCO2.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserRegisterModel {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
