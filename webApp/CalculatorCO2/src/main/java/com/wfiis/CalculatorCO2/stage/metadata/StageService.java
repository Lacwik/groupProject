package com.wfiis.CalculatorCO2.stage.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.metadata.ModuleService;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.metadata.repository.StageRepository;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;

import java.util.ArrayList;
import java.util.List;

public class StageService {
    private StageRepository stageRepository;
    private ModuleService moduleService;
    private VegetableService vegetableService;

    public List<Stage> getOutsourcedStages() {
        return stageRepository.findStagesByOutsourced(1);
    }

    public List<Stage> getCompanyStages(Long companyId){
        return stageRepository.findStagesByCompanyId(companyId);
    }

    public List<StageModel> getModelsFromEntityList(List<Stage> stages) {
        List<StageModel> outStages = new ArrayList<>();
        for (Stage stage : stages) {
            moduleService.getModelsFromEntityList(stage.getModules());
            outStages.add(getModelFromEntity(stage));
        }
        return outStages;
    }

    public StageModel getModelFromEntity(Stage stage) {
        return new StageModel(
                stage.getId(),
                stage.getName(),
                stage.getOutsourced(),
                vegetableService.getModelsFromEntityList(stage.getVegetables())
        );
    }

}
