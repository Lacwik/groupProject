package com.wfiis.CalculatorCO2.stage.model;

import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StageCreateModel {
    private String name;
    private List<ModuleModel> modulesModels;
}
