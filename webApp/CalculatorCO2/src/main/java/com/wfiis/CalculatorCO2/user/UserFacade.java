package com.wfiis.CalculatorCO2.user;

import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.metadata.UserAssembler;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.model.CompanyRoleModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticatedModel;
import com.wfiis.CalculatorCO2.user.model.UserCompanyMember;
import com.wfiis.CalculatorCO2.user.model.UserLoginModel;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import com.wfiis.CalculatorCO2.user.security.authorization.TokenProvider;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserMetadataService userMetadataService;
    private final UserAssembler userAssembler;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final CompanyService companyService;

    public UserProfileModel registerUser(UserRegisterModel userRegisterModel) {

        return userAssembler.convertEntityToModel(userMetadataService.saveUser(userRegisterModel));
    }

    public UserAuthenticatedModel loginUser(UserLoginModel userLoginModel) {
        authenticateUser(userLoginModel);

        String token = tokenProvider.createToken(userLoginModel.getEmail());

        final User user = userMetadataService.findUser(userLoginModel.getEmail());

        return UserAuthenticatedModel.builder()
                .email(userLoginModel.getEmail())
                .role(user.getRole())
                .JWT(token)
                .build();
    }

    public List<CompanyRoleModel> getAllUserRoles(Long userId) {
        final User user = userMetadataService.findUser(userId);
        List<CompanyRoleModel> companyRoleModels = new LinkedList<>();

        companyRoleModels.addAll(userAssembler.convertCompaniesToCompanyRoleModels(user.getCompaniesAdmin(), CompanyRole.ADMIN));
        companyRoleModels.addAll(userAssembler.convertCompaniesToCompanyRoleModels(user.getCompaniesExpert(), CompanyRole.EXPERT));
        companyRoleModels.addAll(userAssembler.convertCompaniesToCompanyRoleModels(user.getCompaniesWorker(), CompanyRole.WORKER));

        return companyRoleModels;
    }

    private void authenticateUser(UserLoginModel model) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                model.getEmail(), model.getPassword());

        authenticationManager.authenticate(authenticationToken);
    }

    public List<UserProfileModel> getUsersBySearchValue(String searchValue, Long companyId) {
        return userAssembler.convertEntitiesToModels(userMetadataService.findUsersBy(searchValue, companyId));
    }

    public void startUserJobForCompanyAsRole(Long userId, Long companyId, CompanyRole role) {
        userMetadataService.startJobForCompanyAsRole(userId, companyId, role);
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    @Transactional
    public void registerUserForCompany(CompanyIdentity companyIdentity, UserCompanyMember userCompanyMember) {
        final User user = userAssembler.convertCompanyMemberToUser(userCompanyMember);

        final Company company = companyService.findCompany(companyIdentity.getCompanyId());

        userMetadataService.saveUser(user);

        switch (userCompanyMember.getCompanyRole()) {
            case ADMIN:
                company.getAdministrators().add(user);
                break;
            case EXPERT:
                company.getExperts().add(user);
                break;
            case WORKER:
                company.getWorkers().add(user);
                break;
        }
    }
}
