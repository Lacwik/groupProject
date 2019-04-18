package com.wfiis.CalculatorCO2.stage.model;

import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class StageModel {
    private Long id;
    private String name;
    private int outsourced;
    private List<VegetableModel> vegetables;
}
