package com.wfiis.CalculatorCO2.user.metadata;

import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.user.exception.UserNotFoundException;
import com.wfiis.CalculatorCO2.user.exception.UserNotWorkingForCompanyException;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.metadata.entity.UserCompanyJob;
import com.wfiis.CalculatorCO2.user.metadata.repository.UserCompanyJobRepository;
import com.wfiis.CalculatorCO2.user.metadata.repository.UserRepository;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import com.wfiis.CalculatorCO2.user.security.authorization.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserMetadataService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserAssembler userAssembler;
    private final UserCompanyJobRepository companyJobRepository;
    private final CompanyService companyService;

    public User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findUsersBy(String searchValue, Long companyId) {
        List<User> users = userRepository.findUsersByNameOrLastNameOrEmail(searchValue, searchValue, searchValue);

        return users.stream()
                .filter(user -> !companyService.isMemberOfCompany(user.getId(), companyId))
                .collect(Collectors.toList());
    }

    public User saveUser(UserRegisterModel userRegisterModel) {
        final User user = userAssembler.convertRegisterToEntity(userRegisterModel);
        encodeUserPassword(user);
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        encodeUserPassword(user);
        return userRepository.save(user);
    }

    private void encodeUserPassword(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Transactional
    public void startJobForCompanyAsRole(Long userId, Long companyId, CompanyRole role) {
        validateUserCanDoHisJob(userId, companyId, role);

        final User user = findUser(userId);
        final Company company = companyService.findCompany(companyId);

        UserCompanyJob userCompanyJob = UserCompanyJob.builder()
                .user(user)
                .company(company)
                .role(role)
                .build();


        companyJobRepository.deleteAllByUserId(userId);

        companyJobRepository.save(userCompanyJob);
    }

    private void validateUserCanDoHisJob(Long userId, Long companyId, CompanyRole role) {
        boolean result = false;

        switch (role) {
            case EXPERT:
                result = companyService.isExpertOfCompany(userId, companyId);
                break;

            case ADMIN:
                result = companyService.isAdminOfCompany(userId, companyId);
                break;

            case WORKER:
                result = companyService.isWorkerOfCompany(userId, companyId);
                break;
        }

        if (!result) {
            throw new ForbiddenException("User is not member of company.");
        }
    }

    public boolean canUserWorkingForCompanyAsRole(Long userId, Long companyId, CompanyRole companyRole) {
        return companyJobRepository.existsByUserIdAndCompanyIdAndRole(userId, companyId, companyRole);
    }

    public boolean canUserWorkingForCompany(Long userId, Long companyId) {
        return companyJobRepository.existsByUserIdAndCompanyId(userId, companyId);
    }

    public Company getCurrentCompanyWorkingFor(Long userId) {
        return companyJobRepository.findByUserId(userId).orElseThrow(()->new UserNotWorkingForCompanyException(userId)).getCompany();
    }
}
