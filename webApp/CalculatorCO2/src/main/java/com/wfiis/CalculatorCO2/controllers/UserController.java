package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.user.UserFacade;
import com.wfiis.CalculatorCO2.user.model.UserProfileModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserFacade userFacade;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserProfileModel>> getUsersBy(@RequestParam(name="search") String searchValue) {
        return ResponseEntity.ok(userFacade.getUsersBySearchValue(searchValue));
    }
}
