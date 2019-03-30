package com.wfiis.CalculatorCO2.company.model;


import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CompanyRegisterRequestModel {
    private final UserProfileModel userProfileModel;
    private final CompanyModel companyModel;
}
