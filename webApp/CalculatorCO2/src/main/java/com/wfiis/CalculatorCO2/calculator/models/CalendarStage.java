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
public class CalendarStage {
    private Duration duration;
    private Long id;
    private Map<String, ObjectValueWithUnit> leftovers;
    private Map<String, ObjectValueWithUnit> resources;
}
