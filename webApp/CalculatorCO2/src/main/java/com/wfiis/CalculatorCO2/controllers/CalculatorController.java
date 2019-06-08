package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.calculator.CalculatorFacade;
import com.wfiis.CalculatorCO2.calculator.models.CalendarFormModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {
    private final CalculatorFacade calculatorFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> calc(UsernamePasswordAuthenticationToken authModel, @RequestBody CalendarFormModel calendarFormModel) {
        log.info("Calculating form model: {}", calendarFormModel);

        final Float result = calculatorFacade.calculate(authModel, calendarFormModel).getCo2PerLine();
        log.info("Result: {}", result);
        return ResponseEntity.ok(result.toString());
    }
}
