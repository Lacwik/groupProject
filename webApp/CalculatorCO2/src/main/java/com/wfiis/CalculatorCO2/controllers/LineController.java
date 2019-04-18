package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.line.LineFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/line")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class LineController {
    private final LineFacade lineFacade;


}
