package com.wfiis.CalculatorCO2.module.model;

import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ModuleModel {
    private Long id;
    private String name;
    private float loss;
    private float waste;
    private int time;
    private Resource resource;
    private List<Vegetable> vegetables;
}
