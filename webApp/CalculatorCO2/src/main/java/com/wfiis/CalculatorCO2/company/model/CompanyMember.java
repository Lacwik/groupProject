package com.wfiis.CalculatorCO2.company.model;

import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CompanyMember {
    private final Long userId;
    private final CompanyRole companyRole;
}
