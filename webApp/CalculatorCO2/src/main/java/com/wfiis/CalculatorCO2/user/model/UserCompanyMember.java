package com.wfiis.CalculatorCO2.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserCompanyMember {
    private final String name;
    private final String lastName;
    private final String email;
    private final Long companyId;
    private final String password;
    private final CompanyRole companyRole;
}
