package com.wfiis.CalculatorCO2.company.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CompanyIdentity {
    private final Long companyId;

    public static CompanyIdentity of(Long companyId) {
        return new CompanyIdentity(companyId);
    }
}
