package com.wfiis.CalculatorCO2.lineStatistics.metadata;

import com.wfiis.CalculatorCO2.lineStatistics.metadata.entity.LineStatistics;
import com.wfiis.CalculatorCO2.lineStatistics.model.LineStatisticsCreateModel;
import com.wfiis.CalculatorCO2.stageResourceValue.metadata.StageResourceValueAssembler;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LineStatisticsAssembler {
    private final StageResourceValueAssembler stageResourceValueAssembler;
    private final VegetableService vegetableService;

    public LineStatisticsCreateModel getCreateModelFromEntity(LineStatistics lineStatistics) {
        return new LineStatisticsCreateModel(
                lineStatistics.getId(),
                lineStatistics.getLine(),
                stageResourceValueAssembler.getCreateModelsFromEntityList(lineStatistics.getStageResourceValues()),
                lineStatistics.getVegetable(),
                lineStatistics.getCarbonPrint(),
                null
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
                stageResourceValueAssembler.getNewEntitiesFromModels(lineStatisticsCreateModel.getStageResourceValueCM()),
                vegetableService.getVegetableEntity(lineStatisticsCreateModel.getVegetable().getId()),
                lineStatisticsCreateModel.getCarbonPrint(),
                lineStatisticsCreateModel.getCompany()

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
