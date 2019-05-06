package com.wfiis.CalculatorCO2.stage.model;

import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.resourceFlags.model.ResourceFlagsCreateModel;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StageCreateModel {
    private String name;
    private float power;
    private float loss;
    private float waste;
    private List<Vegetable> vegetables;
    private List<Module> modules;
}
