package com.wfiis.CalculatorCO2.request.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.request.exception.NotFoundRegisterRequest;
import com.wfiis.CalculatorCO2.request.metadata.entity.UserCompanyRegisterRequest;
import com.wfiis.CalculatorCO2.request.metadata.repository.UserCompanyRegisterRequestRepository;
import com.wfiis.CalculatorCO2.request.metadata.repository.UserRegisterRequestViewRepository;
import com.wfiis.CalculatorCO2.request.model.CompanyRegisterRequestModel;
import com.wfiis.CalculatorCO2.user.metadata.UserAssembler;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterRequestService {
    private final UserCompanyRegisterRequestRepository registerRequestRepository;
    private final UserRegisterRequestViewRepository userRegisterRequestViewRepository;
    private final UserCompanyRegisterRequestAssembler userCompanyRegisterRequestAssembler;
    private final UserAssembler userAssembler;
    private final UserMetadataService userMetadataService;

    public List<CompanyRegisterRequestModel> getUserCompanyRegisterRequests() {
        return userCompanyRegisterRequestAssembler.convertEntityToModel(registerRequestRepository.findAllByIsAcceptedFalse());
    }

    public List<UserProfileModel> getUserRegisterRequests() {
        return userAssembler.convertEntityToModel(userRegisterRequestViewRepository.findAll());
    }

    @Transactional
    public void acceptUserCompanyRequest(Long companyId) {
        UserCompanyRegisterRequest registerRequest = findRegisterRequestByCompanyId(companyId);

        registerRequest.setIsAccepted(true);

        Long userId = registerRequest.getUser().getId();

        acceptUserRequest(userId);
    }

    private UserCompanyRegisterRequest findRegisterRequestByCompanyId(Long companyId) {
        return registerRequestRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new NotFoundRegisterRequest(companyId));
    }

    @Transactional
    public void acceptUserRequest(Long userId) {
        final User user = userMetadataService.findUser(userId);

        user.setIsActive(true);
    }

    public void addUserCompanyRegisterRequest(Company company, User user) {
        UserCompanyRegisterRequest registerRequest = UserCompanyRegisterRequest.builder()
                .company(company)
                .isAccepted(false)
                .user(user)
                .build();

        registerRequestRepository.save(registerRequest);
    }

}
