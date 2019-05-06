package com.wfiis.CalculatorCO2.line.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LineAssembler {
    public List<LineCreateModel> getCreateModelsFromEntityList(List<Line> lines) {
        List<LineCreateModel> outLines = new ArrayList<>();
        for (Line line : lines) {
            outLines.add(getCreateModelFromEntity(line));
        }
        return outLines;
    }

    public LineCreateModel getCreateModelFromEntity(Line line) {
        return new LineCreateModel(
                line.getName(),
                line.getStages(),
                line.getVegetables()
        );
    }

    public List<LineModel> getModelsFromEntityList(List<Line> lines) {
        List<LineModel> outLines = new ArrayList<>();
        for (Line line : lines) {
            outLines.add(getModelFromEntity(line));
        }
        return outLines;
    }

    public LineModel getModelFromEntity(Line line) {
        return new LineModel(
                line.getName(),
                line.getStages(),
                line.getVegetables(),
                line.getId()
        );
    }

    public Line getNewEntityFromModel(LineCreateModel lineCreateModel, Company company){
        return new Line(
                null,
                lineCreateModel.getName(),
                false,
                company,
                lineCreateModel.getStages(),
                lineCreateModel.getVegetables(),
                true
        );
    }
}
