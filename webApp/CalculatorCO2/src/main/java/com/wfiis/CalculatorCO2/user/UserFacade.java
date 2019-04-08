package com.wfiis.CalculatorCO2.user;

import com.wfiis.CalculatorCO2.user.metadata.UserAssembler;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticatedModel;
import com.wfiis.CalculatorCO2.user.model.UserLoginModel;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import com.wfiis.CalculatorCO2.user.security.authorization.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserMetadataService userMetadataService;
    private final UserAssembler userAssembler;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

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

    private void authenticateUser(UserLoginModel model) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                model.getEmail(), model.getPassword());

        authenticationManager.authenticate(authenticationToken);
    }
}
