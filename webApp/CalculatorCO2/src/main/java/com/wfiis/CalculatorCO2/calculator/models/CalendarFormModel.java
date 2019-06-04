package com.wfiis.CalculatorCO2.calculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class CalendarFormModel {
    private ObjectValueWithUnit material;
    private ObjectValueWithUnit product;
    private List<GusResource> resources;
    private List<CalculatorStage> stages;
}
