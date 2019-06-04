package com.wfiis.CalculatorCO2.lineStatistics.metadata;

import com.wfiis.CalculatorCO2.lineStatistics.metadata.entity.LineStatistics;
import com.wfiis.CalculatorCO2.lineStatistics.model.LineStatisticsCreateModel;
import com.wfiis.CalculatorCO2.stageResourceValue.metadata.StageResourceValueAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LineStatisticsAssembler {
    private final StageResourceValueAssembler stageResourceValueAssembler;

    public LineStatisticsCreateModel getCreateModelFromEntity(LineStatistics lineStatistics) {
        return new LineStatisticsCreateModel(
                lineStatistics.getLine(),
                stageResourceValueAssembler.getCreateModelsFromEntityList(lineStatistics.getStageResourceValues())
        );
    }

    public List<LineStatisticsCreateModel> getCreateModelsFromEntityList(List<LineStatistics> lineStatistics) {
        List<LineStatisticsCreateModel> outLSCM = new ArrayList<>();
        for (LineStatistics item : lineStatistics) {
            outLSCM.add(getCreateModelFromEntity(item));
        }
        return outLSCM;
    }

    public LineStatistics getNewEntityFromModel(LineStatisticsCreateModel lineStatisticsCreateModel){
        return new LineStatistics(
                null,
                lineStatisticsCreateModel.getLine(),
                stageResourceValueAssembler.getNewEntitiesFromModels(lineStatisticsCreateModel.getStageResourceValueCM())
        );
    }

    public List<LineStatistics> getNewEntitiesFromModels(List<LineStatisticsCreateModel> lineStatisticsCreateModels){
        List<LineStatistics> outLSs = new ArrayList<>();
        for (LineStatisticsCreateModel item : lineStatisticsCreateModels) {
            outLSs.add(getNewEntityFromModel(item));
        }
        return outLSs;
    }

}
