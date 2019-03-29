package com.wfiis.CalculatorCO2.company.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyRegisterModel extends UserRegisterModel {
    private String companyName;

    @JsonCreator
    public CompanyRegisterModel(String name, String lastName, String email, String password, String companyName) {
        super(name, lastName, email, password);
        this.companyName = companyName;
    }
}
