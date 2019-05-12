package com.wfiis.CalculatorCO2.stage.model;


import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import lombok.Getter;
import java.util.List;

@Getter
public class StageModel extends StageCreateModel {
    private Long id;

    public StageModel(String name, List<Module> modules, Long id) {
        super(name, modules);
        this.id = id;
    }
}