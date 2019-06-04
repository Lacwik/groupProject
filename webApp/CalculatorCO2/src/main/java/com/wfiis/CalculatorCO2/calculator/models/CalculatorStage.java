package com.wfiis.CalculatorCO2.calculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class CalculatorStage {
    private Duration duration;
    private Long id;
    private Map<Long, ObjectValueWithUnit> leftovers;
    private Map<Long, ObjectValueWithUnit> resources;
}
