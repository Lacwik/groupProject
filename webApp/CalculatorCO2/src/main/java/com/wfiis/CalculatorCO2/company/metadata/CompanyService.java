package com.wfiis.CalculatorCO2.company.metadata;

import com.wfiis.CalculatorCO2.company.exceptions.CompanyNotCouldBeFound;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.metadata.entity.CompanyRegisterRequest;
import com.wfiis.CalculatorCO2.company.metadata.repository.CompanyRegisterRequestRepository;
import com.wfiis.CalculatorCO2.company.metadata.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyRegisterRequestRepository registerRequestRepository;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public void addCompanyRegisterRequest(CompanyRegisterRequest registerRequest) {
        registerRequestRepository.save(registerRequest);
    }

    public Company findCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotCouldBeFound(companyId));
    }

    public boolean isExpertOfCompany(Long userId, Long companyId) {
        return companyRepository.existsCompanyByIdAndExpertsId(companyId, userId);
    }

    public boolean isWorkerOfCompany(Long userId, Long companyId) {
        return companyRepository.existsCompanyByIdAndWorkersId(companyId, userId);
    }

    public boolean isAdminOfCompany(Long userId, Long companyId) {
        return companyRepository.existsCompanyByIdAndAdministratorsId(companyId, userId);
    }

    public boolean isMemberOfCompany(Long userId, Long companyId) {
        return isWorkerOfCompany(userId, companyId)
                || isExpertOfCompany(userId, companyId)
                || isAdminOfCompany(userId, companyId);
    }

    public List<CompanyRegisterRequest> getCompanyRegisterRequests() {
        return registerRequestRepository.findAllByIsAcceptedFalse();
    }
}
