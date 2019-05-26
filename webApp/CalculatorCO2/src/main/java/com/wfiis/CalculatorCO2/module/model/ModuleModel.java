package com.wfiis.CalculatorCO2.module.model;

import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.Getter;

import java.util.List;

@Getter
public class ModuleModel extends ModuleCreateModel {
    private Long id;

    public ModuleModel(String name, float power, List<Vegetable> vegetables, List<Resource> resources, List<Leftover> leftovers, Long id) {
        super(name, power, vegetables, resources, leftovers);
        this.id = id;
    }
}
