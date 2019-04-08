package com.wfiis.CalculatorCO2.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserAuthenticatedModel {
    private String email;
    private String JWT;
    private Role role;
}
