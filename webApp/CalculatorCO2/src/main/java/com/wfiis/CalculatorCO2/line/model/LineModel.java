package com.wfiis.CalculatorCO2.line.model;

import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import lombok.Getter;

import java.util.List;

@Getter
public class LineModel extends LineCreateModel {
    private Long id;

    public LineModel(String name, List<Stage> stages, Long id) {
        super(name, stages);
        this.id = id;
    }
}
