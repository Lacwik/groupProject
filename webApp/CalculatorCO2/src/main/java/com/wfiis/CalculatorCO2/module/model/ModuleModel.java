package com.wfiis.CalculatorCO2.module.model;

import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.Getter;

import java.util.List;

@Getter
public class ModuleModel extends ModuleCreateModel {
    private Long id;

    public ModuleModel(String name, float loss, float waste, int time, Resource resource, List<Vegetable> vegetables, Long id) {
        super(name, loss, waste, time, resource, vegetables);
        this.id = id;
    }
}
