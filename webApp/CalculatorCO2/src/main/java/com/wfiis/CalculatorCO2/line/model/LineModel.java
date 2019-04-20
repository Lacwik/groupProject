package com.wfiis.CalculatorCO2.line.model;

import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.Getter;

import java.util.List;

@Getter
public class LineModel extends LineCreateModel {
    private Long id;

    public LineModel(String name, List<Stage> stages, List<Vegetable> vegetables, Long id) {
        super(name, stages, vegetables);
        this.id = id;
    }
}
