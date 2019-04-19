package com.wfiis.CalculatorCO2.line.model;

import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LineModel {
    private Long id;

    private String name;

    private int outsourced;

    private List<StageCreateModel> stages;

    private List<VegetableModel> vegetables;
}
