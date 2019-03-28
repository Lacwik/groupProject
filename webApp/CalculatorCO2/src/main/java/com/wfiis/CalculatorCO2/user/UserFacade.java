package com.wfiis.CalculatorCO2.user;

import com.wfiis.CalculatorCO2.user.assembler.UserAssembler;
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
        // add validation before register user

        User user = userAssembler.convertRegisterToModel(userRegisterModel);

        return userAssembler.convertEntityToModel(userMetadataService.saveUser(user));
    }

    public UserAuthenticatedModel loginUser(UserLoginModel userLoginModel) {
        authenticateUser(userLoginModel);

        String token = tokenProvider.createToken(userLoginModel.getEmail());

        return UserAuthenticatedModel.builder()
                .email(userLoginModel.getEmail())
                .JWT(token)
                .build();
    }

    private void authenticateUser(UserLoginModel model) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                model.getEmail(), model.getPassword());

        authenticationManager.authenticate(authenticationToken);
    }

}
