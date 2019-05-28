package com.wfiis.CalculatorCO2.line.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageAssembler;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LineAssembler {
    private final StageAssembler stageAssembler;

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
                stageAssembler.getModelsFromEntityList(line.getStages())
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
                stageAssembler.getModelsFromEntityList(line.getStages()),
                line.getId()
        );
    }

    public Line getNewEntityFromModel(LineCreateModel lineCreateModel, Company company){
        List<Stage> stages = new ArrayList<>();

        for (StageModel stageModel : lineCreateModel.getStageModels())
            stages.add(stageAssembler.getEntity(stageModel.getId()));

        return new Line(
                null,
                lineCreateModel.getName(),
                false,
                false,
                company,
                stages
        );
    }
}
