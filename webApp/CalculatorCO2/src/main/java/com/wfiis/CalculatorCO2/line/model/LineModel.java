package com.wfiis.CalculatorCO2.line.model;

import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LineModel {
    private Long id;

    private String name;

    private int outsourced;

    private List<StageModel> stages;

    private List<VegetableModel> vegetables;
}
