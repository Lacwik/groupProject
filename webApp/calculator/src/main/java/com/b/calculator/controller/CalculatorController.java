package com.b.calculator.controller;

import com.b.calculator.repository.VegetableRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculatorController {

    @Autowired
    VegetableRepository vegetableRepository;
}
