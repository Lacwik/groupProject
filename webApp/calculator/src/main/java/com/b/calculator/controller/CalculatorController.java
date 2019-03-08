package com.b.calculator.controller;

import com.b.calculator.repository.VegetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class CalculatorController {

    @Autowired
    VegetableRepository vegetableRepository;


    @GetMapping("/home")
    public String home()
    {
         return "home";
    }
}
