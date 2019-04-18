package com.wfiis.CalculatorCO2.module.model;

import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ModuleModel {
    private Long id;
    private String name;
    private float loss;
    private float waste;
    private float power;
    private int time;
    private int outsourced;
    private Long resource;
    private List<VegetableModel> vegetables;
}
