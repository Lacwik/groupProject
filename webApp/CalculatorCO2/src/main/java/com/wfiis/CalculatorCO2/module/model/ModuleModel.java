package com.wfiis.CalculatorCO2.module.model;

import com.wfiis.CalculatorCO2.resourceFlags.model.ResourceFlagsCreateModel;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.Getter;

import java.util.List;

@Getter
public class ModuleModel extends ModuleCreateModel {
    private Long id;

    public ModuleModel(String name, float power, ResourceFlagsCreateModel resourceFlagsCreateModel, List<Vegetable> vegetables, Long id) {
        super(name, power, resourceFlagsCreateModel, vegetables);
        this.id = id;
    }
}
