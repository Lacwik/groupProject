package com.wfiis.CalculatorCO2.company.model;

import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class CompanyRegisterModel extends UserRegisterModel {
    private String companyName;
}
