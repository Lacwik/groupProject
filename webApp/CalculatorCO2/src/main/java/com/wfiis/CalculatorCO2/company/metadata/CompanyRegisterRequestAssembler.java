package com.wfiis.CalculatorCO2.company.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.CompanyRegisterRequest;
import com.wfiis.CalculatorCO2.company.model.CompanyRegisterRequestModel;
import com.wfiis.CalculatorCO2.user.metadata.UserAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyRegisterRequestAssembler {
    private final UserAssembler userAssembler;
    private final CompanyAssembler companyAssembler;

    public CompanyRegisterRequestModel convertEntityToModel(CompanyRegisterRequest registerRequest) {
        return CompanyRegisterRequestModel.builder()
                .companyModel(companyAssembler.convertEntityToModel(registerRequest.getCompany()))
                .userProfileModel(userAssembler.convertEntityToModel(registerRequest.getUser()))
                .build();
    }

    public List<CompanyRegisterRequestModel> convertEntityToModel(List<CompanyRegisterRequest> registerRequests) {
        return registerRequests.stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }
}
