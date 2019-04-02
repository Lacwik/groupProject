package com.wfiis.CalculatorCO2.company.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyModel;
import com.wfiis.CalculatorCO2.company.model.CompanyRegisterModel;
import com.wfiis.CalculatorCO2.user.metadata.UserAssembler;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CompanyAssembler {
    private final UserAssembler userAssembler;

    public Company convertRegisterModelToEntity(CompanyRegisterModel registerModel, User user) {
        return Company.builder()
                .name(registerModel.getCompanyName())
                .experts(Collections.emptyList())
                .workers(Collections.emptyList())
                .administrators(Collections.singletonList(user))
                .build();
    }

    public CompanyModel convertEntityToModel(Company company) {
        return CompanyModel.builder()
                .name(company.getName())
                .administrators(userAssembler.convertEntitiesToSimpleModel(company.getAdministrators()))
                .experts(userAssembler.convertEntitiesToSimpleModel(company.getExperts()))
                .workers(userAssembler.convertEntitiesToSimpleModel(company.getWorkers()))
                .build();
    }
}
