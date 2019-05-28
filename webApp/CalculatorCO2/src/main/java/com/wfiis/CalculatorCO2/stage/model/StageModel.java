package com.wfiis.CalculatorCO2.stage.model;


import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class StageModel extends StageCreateModel {
    private Long id;

    public StageModel(String name, List<ModuleModel> modulesModels, Long id) {
        super(name, modulesModels);
        this.id = id;
    }
}