package com.wfiis.CalculatorCO2.line.metadata;

import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.line.metadata.repository.LineRepository;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageAssembler;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;

import java.util.ArrayList;
import java.util.List;

public class LineService {
    private LineRepository lineRepository;
    private StageService stageService;
    private VegetableService vegetableService;
    private StageAssembler stageAssembler;


    public List<LineCreateModel> getModelsFromEntityList(List<Line> lines) {
        List<LineCreateModel> outLines = new ArrayList<>();

        for (Line line : lines) {
            outLines.add(getModelFromEntity(line));
        }

        return outLines;
    }

    public LineCreateModel getModelFromEntity(Line line) {
        return new LineCreateModel(
                line.getId(),
                line.getName(),
                line.getOutsourced(),
                line.getStages(),
                line.getVegetables()
        );
    }
}
