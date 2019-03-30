package com.wfiis.CalculatorCO2.request.metadata;

import com.wfiis.CalculatorCO2.company.metadata.CompanyAssembler;
import com.wfiis.CalculatorCO2.request.model.CompanyRegisterRequestModel;
import com.wfiis.CalculatorCO2.request.metadata.entity.UserCompanyRegisterRequest;
import com.wfiis.CalculatorCO2.user.metadata.UserAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserCompanyRegisterRequestAssembler {
    private final UserAssembler userAssembler;
    private final CompanyAssembler companyAssembler;

    public CompanyRegisterRequestModel convertEntityToModel(UserCompanyRegisterRequest registerRequest) {
        return CompanyRegisterRequestModel.builder()
                .companyModel(companyAssembler.convertEntityToModel(registerRequest.getCompany()))
                .userProfileModel(userAssembler.convertEntityToModel(registerRequest.getUser()))
                .build();
    }

    public List<CompanyRegisterRequestModel> convertEntityToModel(List<UserCompanyRegisterRequest> registerRequests) {
        return registerRequests.stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }
}
