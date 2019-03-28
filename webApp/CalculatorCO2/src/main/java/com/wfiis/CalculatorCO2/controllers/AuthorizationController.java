package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.user.UserFacade;
import com.wfiis.CalculatorCO2.user.model.UserLoginModel;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class AuthorizationController {
    private final UserFacade userFacade;

    @PostMapping(
            value = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileModel> registerUser(@RequestBody UserRegisterModel model) {

        try {
            return ResponseEntity.ok(userFacade.registerUser(model));
        } catch (Exception e) {
            log.error("Caught exception: {} while try to register new user: {}.", e.getMessage(), model);
            throw e;
        }
    }

    @PostMapping(
            value ="/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody UserLoginModel model) {

        try {
            return ResponseEntity.ok(userFacade.loginUser(model));
        }
        catch (AuthenticationException e) {
            log.info("User: {} is unauthorized: {}.", model, e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You typed wrong email or password.");
        }
        catch (Exception e) {
            log.error("UserLoginModel: {}, error caught due to: {}", model, e.getMessage(), e);
            throw e;
        }
    }
}
