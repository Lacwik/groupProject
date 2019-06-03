package com.wfiis.CalculatorCO2.stageResourceValue.metadata;

import com.wfiis.CalculatorCO2.stageResourceValue.metadata.entity.StageResourceValue;
import com.wfiis.CalculatorCO2.stageResourceValue.model.StageResourceValueCreateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StageResourceValueAssembler {

    public StageResourceValueCreateModel getCreateModelFromEntity(StageResourceValue stageResourceValue) {
        return new StageResourceValueCreateModel(
                stageResourceValue.getResource(),
                stageResourceValue.getValue(),
                stageResourceValue.getStage(),
                stageResourceValue.getTime()
        );
    }

    public List<StageResourceValueCreateModel> getCreateModelsFromEntityList(List<StageResourceValue> SRVs) {
        List<StageResourceValueCreateModel> outSRV = new ArrayList<>();
        for (StageResourceValue srv : SRVs) {
            outSRV.add(getCreateModelFromEntity(srv));
        }
        return outSRV;
    }

    public StageResourceValue getNewEntityFromModel(StageResourceValueCreateModel stageResourceValueCreateModel){
        return new StageResourceValue(
                null,
                stageResourceValueCreateModel.getResource(),
                stageResourceValueCreateModel.getValue(),
                stageResourceValueCreateModel.getStage(),
                stageResourceValueCreateModel.getTime()
        );
    }

    public List<StageResourceValue> getNewEntitiesFromModels(List<StageResourceValueCreateModel> stageResourceValueCreateModels){
        List<StageResourceValue> outSRV = new ArrayList<>();
        for (StageResourceValueCreateModel srvcr : stageResourceValueCreateModels) {
            outSRV.add(getNewEntityFromModel(srvcr));
        }
        return outSRV;
    }
}
