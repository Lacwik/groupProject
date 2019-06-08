package com.wfiis.CalculatorCO2.calculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class EmittedCo2 {
    private final Map<Long, Float> co2PerStage;
    private final Float co2PerLine;
}
