package com.wfiis.CalculatorCO2.company.metadata;

import com.wfiis.CalculatorCO2.company.exceptions.CompanyNotCouldBeFound;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.metadata.repository.CompanyRepository;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.company.model.CompanyMember;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserMetadataService userMetadataService;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
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

    @Transactional
    public void addMemberToCompany(CompanyIdentity companyIdentity, CompanyMember companyMember) {
        final Company company = findCompany(companyIdentity.getCompanyId());
        final User user = userMetadataService.findUser(companyMember.getUserId());

        addUserToRole(company, user, companyMember);
    }

    private void addUserToRole(Company company, User user, CompanyMember companyMember) {
        switch (companyMember.getCompanyRole()) {
            case EXPERT:
                company.getExperts().add(user);
                break;
            case WORKER:
                company.getWorkers().add(user);
                break;
            case ADMIN:
                company.getAdministrators().add(user);
                break;
        }
    }
}
