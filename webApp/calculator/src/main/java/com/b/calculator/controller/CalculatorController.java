package com.b.calculator.controller;

import com.b.calculator.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculatorController {

    @Autowired
    VegetableRepository vegetableRepository;

    @Autowired
    LineRepository lineRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    StageRepository stageRepository;


    @GetMapping("/home")
    public String home()
    {
         return "home";
    }


}
