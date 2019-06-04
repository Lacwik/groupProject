package com.wfiis.CalculatorCO2.line.model;

import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import lombok.Getter;

import java.util.List;

@Getter
public class LineModel extends LineCreateModel {
    private Long id;

    public LineModel(String name, List<StageModel> stageModels, String stagesOrder, Long id) {
        super(name, stageModels, stagesOrder);
        this.id = id;
    }
}
