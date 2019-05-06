package com.wfiis.CalculatorCO2.module.model;

import com.wfiis.CalculatorCO2.resourceFlags.model.ResourceFlagsCreateModel;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModuleCreateModel {
    private String name;
    private float power;
    private ResourceFlagsCreateModel resourceFlagsCreateModel;
    private List<Vegetable> vegetables;
}
