package com.wfiis.CalculatorCO2.module.model;

import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
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
    private List<Vegetable> vegetables;
    private List<Resource> resources;
    private List<Leftover> leftovers;
}
