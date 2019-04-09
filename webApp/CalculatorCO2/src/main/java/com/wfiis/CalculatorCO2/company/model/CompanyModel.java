package com.wfiis.CalculatorCO2.company.model;

import com.wfiis.CalculatorCO2.user.model.UserSimpleModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CompanyModel {
    private final String name;
    private final Long id;
    private List<UserSimpleModel> workers;
    private List<UserSimpleModel> experts;
    private List<UserSimpleModel> administrators;

}
