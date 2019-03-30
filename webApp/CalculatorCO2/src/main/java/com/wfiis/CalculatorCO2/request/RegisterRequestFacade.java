package com.wfiis.CalculatorCO2.request;

import com.wfiis.CalculatorCO2.request.metadata.RegisterRequestService;
import com.wfiis.CalculatorCO2.request.model.CompanyRegisterRequestModel;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterRequestFacade {
    private final RegisterRequestService registerRequestService;

    public List<CompanyRegisterRequestModel> getUserCompanyRegisterRequests() {
        return registerRequestService.getUserCompanyRegisterRequests();
    }

    public List<UserProfileModel> getUserRegisterRequests() {
        return registerRequestService.getUserRegisterRequests();
    }

    public void acceptUserRequest(Long userId) {
        registerRequestService.acceptUserRequest(userId);
    }

    public void acceptUserCompanyRequest(Long companyId) {
        registerRequestService.acceptUserCompanyRequest(companyId);
    }
}

