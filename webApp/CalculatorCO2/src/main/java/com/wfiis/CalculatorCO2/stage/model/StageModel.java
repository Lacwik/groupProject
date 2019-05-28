package com.wfiis.CalculatorCO2.stage.model;


import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import lombok.Getter;
import java.util.List;

@Getter
public class StageModel extends StageCreateModel {
    private Long id;

    public StageModel(String name, List<ModuleModel> modulesModels, Long id) {
        super(name, modulesModels);
        this.id = id;
    }
}