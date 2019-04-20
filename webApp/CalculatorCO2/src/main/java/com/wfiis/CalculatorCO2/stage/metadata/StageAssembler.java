package com.wfiis.CalculatorCO2.stage.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StageAssembler {

    public List<StageCreateModel> getCreateModelsFromEntityList(List<Stage> stages) {
        List<StageCreateModel> outStages = new ArrayList<>();
        for (Stage stage : stages) {
            outStages.add(getCreateModelFromEntity(stage));
        }
        return outStages;
    }

    public StageCreateModel getCreateModelFromEntity(Stage stage) {
        return new StageCreateModel(
                stage.getName(),
                stage.getVegetables(),
                stage.getModules()
        );
    }

    public List<StageModel> getModelsFromEntityList(List<Stage> stages) {
        List<StageModel> outStages = new ArrayList<>();
        for (Stage stage : stages) {
            outStages.add(getModelFromEntity(stage));
        }
        return outStages;
    }

    public StageModel getModelFromEntity(Stage stage) {
        return new StageModel(
                stage.getName(),
                stage.getVegetables(),
                stage.getModules(),
                stage.getId()
        );
    }

    public Stage getNewEntityFromModel(StageCreateModel stageCreateModel, Company company){
        return new Stage(
                null,
                stageCreateModel.getName(),
                false,
                company,
                stageCreateModel.getVegetables(),
                stageCreateModel.getModules(),
                new ArrayList<>()
        );
    }

}
