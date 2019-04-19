package com.wfiis.CalculatorCO2.stage.model;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class StageCreateModel {
    private String name;
    private List<Vegetable> vegetables;
    private List<Module> modules;
}
