package com.wfiis.CalculatorCO2.line.model;

import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LineCreateModel {
    private String name;

    private List<Stage> stages;

    private List<Vegetable> vegetables;
}
